#!/usr/bin/env python3
"""اعتبارسنج Course Package اختصاصی Kotlin.

این ابزار منطق عمومی Core را جایگزین نمی‌کند؛ فقط قبل از انتشار، integrity محتوای همین ریپو را بررسی می‌کند.
"""

import json
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
COURSE = ROOT / "course"


def load(path: Path):
    """یک فایل JSON را با UTF-8 می‌خواند."""
    with path.open("r", encoding="utf-8") as handle:
        return json.load(handle)


def fail(message: str) -> None:
    """اعتبارسنجی را با پیام واضح متوقف می‌کند."""
    raise SystemExit(f"ERROR: {message}")


def main() -> None:
    """ساختار، Stable IDها و referenceهای اصلی دوره را بررسی می‌کند."""
    required = ["manifest.json", "course.json", "levels.json", "chapters.json"]
    for name in required:
        if not (COURSE / name).is_file():
            fail(f"missing {name}")

    levels = load(COURSE / "levels.json")
    chapters = load(COURSE / "chapters.json")
    exercises = load(COURSE / "exercises" / "exercises.json")
    quizzes = load(COURSE / "quizzes" / "quizzes.json")
    projects = load(COURSE / "projects" / "projects.json")

    lessons = [load(path) for path in sorted((COURSE / "lessons").glob("*.json"))]

    level_ids = {item["id"] for item in levels}
    chapter_ids = {item["id"] for item in chapters}
    lesson_ids = {item["id"] for item in lessons}

    collections = [levels, chapters, lessons, exercises, quizzes, projects]
    all_ids = [item["id"] for group in collections for item in group]
    if len(all_ids) != len(set(all_ids)):
        fail("duplicate stable ID detected")

    for chapter in chapters:
        if chapter["levelId"] not in level_ids:
            fail(f"invalid levelId in {chapter['id']}")

    for lesson in lessons:
        if lesson["chapterId"] not in chapter_ids:
            fail(f"invalid chapterId in {lesson['id']}")
        if not lesson.get("objectives") or not lesson.get("blocks"):
            fail(f"lesson is incomplete: {lesson['id']}")

    for item in exercises + quizzes:
        if item["lessonId"] not in lesson_ids:
            fail(f"invalid lessonId in {item['id']}")

    for project in projects:
        if project["levelId"] not in level_ids:
            fail(f"invalid project levelId in {project['id']}")

    print(f"OK: {len(levels)} levels, {len(chapters)} chapters, {len(lessons)} lessons, "
          f"{len(exercises)} exercises, {len(quizzes)} quizzes, {len(projects)} projects")


if __name__ == "__main__":
    main()
