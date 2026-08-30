#!/usr/bin/env python3
"""اعتبارسنج Course Package اختصاصی Kotlin.

منطق عمومی قرارداد Course Package باید در AS-Academy-Core بماند. این ابزار یک quality gate
اختصاصی برای محتوای Kotlin است و integrity، referenceها و حداقل کیفیت assessment را بررسی می‌کند.
"""

import json
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
COURSE = ROOT / "course"
SEMVER = re.compile(r"^\d+\.\d+\.\d+$")
ALLOWED_BLOCKS = {
    "TITLE", "SUBTITLE", "PARAGRAPH", "LIST", "TABLE", "IMAGE", "DIAGRAM", "CODE",
    "OUTPUT", "NOTE", "TIP", "WARNING", "IMPORTANT", "EXERCISE", "QUIZ", "PROJECT_LINK", "REFERENCE"
}


def load(path: Path):
    """یک فایل JSON را با UTF-8 می‌خواند."""
    with path.open("r", encoding="utf-8") as handle:
        return json.load(handle)


def fail(message: str) -> None:
    """اعتبارسنجی را با پیام واضح متوقف می‌کند."""
    raise SystemExit(f"ERROR: {message}")


def require_unique(items, label: str) -> None:
    """Stable IDهای یک collection را یکتا نگه می‌دارد."""
    ids = [item.get("id") for item in items]
    if None in ids or "" in ids:
        fail(f"missing ID in {label}")
    if len(ids) != len(set(ids)):
        fail(f"duplicate stable ID in {label}")


def main() -> None:
    """ساختار، Stable ID، reference، quiz و کیفیت پایه lessonها را بررسی می‌کند."""
    required_files = ["manifest.json", "course.json", "levels.json", "chapters.json"]
    required_dirs = ["lessons", "exercises", "quizzes", "projects", "glossary", "assets", "branding"]
    for name in required_files:
        if not (COURSE / name).is_file():
            fail(f"missing {name}")
    for name in required_dirs:
        if not (COURSE / name).is_dir():
            fail(f"missing directory: course/{name}")

    manifest = load(COURSE / "manifest.json")
    course_meta = load(COURSE / "course.json")
    levels = load(COURSE / "levels.json")
    chapters = load(COURSE / "chapters.json")
    exercises = load(COURSE / "exercises" / "exercises.json")
    quizzes = load(COURSE / "quizzes" / "quizzes.json")
    projects = load(COURSE / "projects" / "projects.json")
    lessons = [load(path) for path in sorted((COURSE / "lessons").glob("*.json"))]

    if manifest.get("courseId") != "kotlin":
        fail("manifest courseId must be kotlin")
    if not SEMVER.match(str(manifest.get("version", ""))):
        fail("manifest version must be SemVer x.y.z")
    if not SEMVER.match(str(manifest.get("minimumCoreVersion", ""))):
        fail("minimumCoreVersion must be SemVer x.y.z")
    if not manifest.get("rtl"):
        fail("Kotlin Persian course must declare rtl=true")

    for label, items in [("levels", levels), ("chapters", chapters), ("lessons", lessons),
                         ("exercises", exercises), ("quizzes", quizzes), ("projects", projects)]:
        require_unique(items, label)

    all_ids = [item["id"] for group in [levels, chapters, lessons, exercises, quizzes, projects] for item in group]
    if len(all_ids) != len(set(all_ids)):
        fail("duplicate stable ID across collections")

    level_ids = {item["id"] for item in levels}
    chapter_ids = {item["id"] for item in chapters}
    lesson_ids = {item["id"] for item in lessons}

    for chapter in chapters:
        if chapter.get("levelId") not in level_ids:
            fail(f"invalid levelId in {chapter['id']}")

    for lesson in lessons:
        if lesson.get("chapterId") not in chapter_ids:
            fail(f"invalid chapterId in {lesson['id']}")
        if not lesson.get("title") or not lesson.get("objectives") or not lesson.get("blocks"):
            fail(f"lesson is incomplete: {lesson['id']}")
        if int(lesson.get("durationMin", 0)) <= 0:
            fail(f"invalid durationMin in {lesson['id']}")
        for block in lesson["blocks"]:
            if block.get("type") not in ALLOWED_BLOCKS:
                fail(f"invalid block type {block.get('type')} in {lesson['id']}")

    for exercise in exercises:
        if exercise.get("lessonId") not in lesson_ids:
            fail(f"invalid lessonId in {exercise['id']}")
        if not exercise.get("acceptance"):
            fail(f"exercise missing acceptance criteria: {exercise['id']}")

    for quiz in quizzes:
        if quiz.get("lessonId") not in lesson_ids:
            fail(f"invalid lessonId in {quiz['id']}")
        options = quiz.get("options", [])
        index = quiz.get("correctIndex")
        if len(options) < 2 or not isinstance(index, int) or not 0 <= index < len(options):
            fail(f"invalid quiz options/correctIndex: {quiz['id']}")
        if not quiz.get("explanation"):
            fail(f"quiz missing explanation: {quiz['id']}")

    for project in projects:
        if project.get("levelId") not in level_ids:
            fail(f"invalid project levelId in {project['id']}")
        if not project.get("deliverables") or not project.get("skills"):
            fail(f"project incomplete: {project['id']}")

    declared_levels = course_meta.get("levels")
    if isinstance(declared_levels, list):
        declared_ids = {item.get("id") if isinstance(item, dict) else item for item in declared_levels}
        if declared_ids and declared_ids != level_ids:
            fail("course.json level IDs do not match levels.json")

    print(
        f"OK: course {manifest['version']} | {len(levels)} levels, {len(chapters)} chapters, "
        f"{len(lessons)} lessons, {len(exercises)} exercises, {len(quizzes)} quizzes, {len(projects)} projects"
    )


if __name__ == "__main__":
    main()
