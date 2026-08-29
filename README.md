# AS Academy - Kotlin

Course Package فارسی Kotlin برای مجموعه AS Academy، از مبانی زبان تا Android، Backend، Multiplatform و مباحث Production.

## معماری

این ریپو مصرف‌کننده `AS-Academy-Core` است. Navigation، Design System، Database infrastructure، Progress، Quiz/Exercise/Project engines، Search، Bookmark، Glossary، Settings، Profile/Drawer، Content/Update/Backup و Code Runner framework در Core قرار دارند و در اینجا کپی نمی‌شوند.

این ریپو فقط Course Package، درس‌ها، مثال‌ها، تمرین‌ها، Quizها، پروژه‌ها، Solutionهای آموزشی، Glossary، Branding و configuration اختصاصی Kotlin را نگه می‌دارد.

## مسیر دوره

1. **مبانی** — ابزارها، syntax، type system پایه، Null Safety، control flow و function.
2. **مقدماتی** — Collections، functional style، OOP، encapsulation، extension، exception، file و testing.
3. **پیشرفته** — Generics/Variance، Sealed Types، Delegation، Inline/Reified، Coroutines، Flow، Channels/Mutex، Gradle/DSL، Reflection/Interop/Performance.
4. **تخصصی** — Android/Compose/Room/Offline-first، Ktor/Spring/Database/Auth، KMP/Compose Multiplatform، JS/Native، Architecture، Security، CI/CD و Observability.

## ساختار

```text
AS-Academy-Kotlin/
├── course/
│   ├── manifest.json
│   ├── course.json
│   ├── levels.json
│   ├── chapters.json
│   ├── lessons/
│   ├── exercises/
│   ├── quizzes/
│   ├── projects/
│   ├── glossary/
│   ├── branding/
│   └── assets/
├── examples/
├── solutions/
├── docs/
├── tools/validate_course.py
├── .github/workflows/validate-course.yml
└── CHANGELOG.md
```

## کنترل کیفیت

CI تمام JSONها، فایل‌های الزامی، یکتایی Stable ID و referenceهای level/chapter/lesson/project را بررسی می‌کند. برای اجرای محلی:

```bash
python3 tools/validate_course.py
```

## قرارداد محتوا

Stable IDهای منتشرشده تغییر نمی‌کنند. هر قابلیت مشترک جدید باید ابتدا در `AS-Academy-Core` پیاده‌سازی شود. تغییر محتوای Kotlin طبق SemVer در `course/manifest.json` و `CHANGELOG.md` نسخه‌گذاری می‌شود.

## منابع مرجع

مستندات رسمی Kotlin و Kotlin Releases، Android/Jetpack Compose، Ktor، Spring و Kotlin Multiplatform منابع اصلی بازبینی فنی این Course Package هستند.

Reference language line: Kotlin 2.4.x.
