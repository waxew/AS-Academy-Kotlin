# AS Academy - Kotlin

Course Package فارسی Kotlin برای مجموعه AS Academy، از مبانی زبان تا Android، Backend، Multiplatform و مهندسی Production.

## وضعیت نسخه 1.2.0

- 4 سطح آموزشی: مبانی، مقدماتی، پیشرفته و تخصصی
- 12 فصل و 46 درس
- 22 تمرین دارای acceptance criteria
- 30 سؤال Quiz با explanation و سناریوهای Advanced/Specialist
- 8 پروژه مرحله‌ای شامل Production Capstone
- Solutionهای canonical/rubric برای مقایسه بعد از تلاش هنرجو
- Reference language: Kotlin 2.4.10
- Android CI: validation، lint، unit test، Debug APK، Release Candidate APK/AAB و SHA-256

## معماری

این ریپو مصرف‌کننده `AS-Academy-Core` است. Navigation، Design System، Database infrastructure، Progress، Quiz/Exercise/Project engines، Search، Bookmark، Glossary، Settings، Profile/Drawer، Content/Update/Backup و Code Runner framework در Core قرار دارند و در اینجا کپی نمی‌شوند.

این ریپو فقط Course Package، درس‌ها، مثال‌ها، تمرین‌ها، Quizها، پروژه‌ها، Solutionهای آموزشی، Glossary، Branding و configuration اختصاصی Kotlin را نگه می‌دارد.

## مسیر دوره

1. **مبانی** — نصب و ابزارها، syntax، type system پایه، Null Safety، control flow، function، debugging و مبانی تست.
2. **مقدماتی** — Collections، functional style، OOP، encapsulation، extension، exception، file، value class و testing.
3. **پیشرفته** — Generics/Variance، Sealed Types، Delegation، Inline/Reified، Structured Concurrency، Flow، Channels/Mutex، Gradle/DSL، Contracts، Coroutine Testing، DI، Modularization و JVM Performance.
4. **تخصصی** — Android/Compose/Room/Offline-first، Ktor/Database/Auth، KMP/Compose Multiplatform، JS/Native، Security، CI/CD، Observability، Resilience، Android Quality و Release Engineering.

## ساختار

```text
AS-Academy-Kotlin/
├── app/                         # Android entry point؛ مصرف‌کننده Core
├── academy-core/                # submodule به AS-Academy-Core
├── course/
│   ├── manifest.json
│   ├── course.json
│   ├── levels.json
│   ├── chapters.json
│   ├── lessons/
│   ├── exercises/
│   ├── quizzes/
│   ├── projects/
│   ├── solutions/
│   ├── glossary/
│   ├── branding/
│   └── assets/
├── docs/
├── tools/validate_course.py
├── .github/workflows/
└── CHANGELOG.md
```

## تجربه آموزشی داخل Android

اپ Android از Academy Core برای نمایش Course Package استفاده می‌کند. قابلیت‌های مشترک شامل مسیر درس‌ها، Progress persistence، Quiz و تاریخچه نتیجه، Exercise/Project screens، Search، Bookmark، Notes، Glossary، Settings، Drawer/Profile، Backup/Update infrastructure و Code Runner framework در Core نگهداری می‌شوند تا همه دوره‌های AS Academy رفتار یکسان داشته باشند.

Course Package کامل در زمان CI به `app/src/main/assets/course/kotlin` sync می‌شود؛ بنابراین `course.json`، metadata، lessons، assessments، projects، glossary، branding، assets و solution metadata همراه build قرار می‌گیرند.

## کنترل کیفیت

`tools/validate_course.py` علاوه بر JSON validity، موارد زیر را بررسی می‌کند:

- یکتایی Stable IDها در collectionها و بین collectionها
- ارتباط Level → Chapter → Lesson و assessmentها
- SemVer نسخه Course/Core
- block typeهای مجاز
- duration/objectives/content پایه درس
- acceptance criteria تمرین‌ها
- صحت options/correctIndex/explanation در Quiz
- کامل بودن deliverable/skill پروژه‌ها
- همسان بودن Level IDهای `course.json` و `levels.json`

برای اجرای محلی:

```bash
python3 tools/validate_course.py
```

## Build و Release

GitHub Actions در هر push به `main` دوره را validate کرده و سپس Android lint، unit tests، Debug APK و Release Candidate APK/AAB را می‌سازد. برای artifactها SHA-256 تولید می‌شود.

Release Candidate تا زمانی که keystore دائمی انتشار در محیط امن CI تنظیم نشده باشد unsigned است. signing secret یا keystore خصوصی نباید داخل repository قرار گیرد. Build از متغیرهای خارجی `AS_RELEASE_STORE_FILE`، `AS_RELEASE_STORE_PASSWORD`، `AS_RELEASE_KEY_ALIAS` و `AS_RELEASE_KEY_PASSWORD` پشتیبانی می‌کند.

## قرارداد محتوا

Stable IDهای منتشرشده تغییر نمی‌کنند. هر قابلیت مشترک جدید ابتدا در `AS-Academy-Core` پیاده‌سازی می‌شود. تغییر محتوای Kotlin طبق SemVer در `course/manifest.json` و `CHANGELOG.md` نسخه‌گذاری می‌شود.

## منابع مرجع

مستندات رسمی Kotlin/Kotlin Releases، Android/Jetpack Compose، Ktor و Kotlin Multiplatform منابع اصلی بازبینی فنی Course Package هستند.

Reference language version: **Kotlin 2.4.10**.
