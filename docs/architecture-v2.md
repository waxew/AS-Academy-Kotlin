# AS Academy Architecture v2 - Kotlin

از این نسخه، اپ Kotlin مطابق معماری چهارلایه AS Academy نگهداری می‌شود.

## لایه‌ها

| لایه | Repository | مالکیت |
|---|---|---|
| Engine | `AS-Academy-Core` | Database, Progress, Search, Quiz, Exercise, Project, Settings, Update, shared services |
| UI Shell | `AS-Academy-MainUi` | Design System, Screens, Navigation presentation, Drawer/Profile, reusable UI |
| Content | `AS-Academy-MainCourse` | Course Packages, lessons, quizzes, exams, exercises, projects, glossary |
| Kotlin App | `AS-Academy-Kotlin` | applicationId, version, Kotlin branding/capabilities, Android launcher |

## Migration rule

دایرکتوری `course/` فعلی تا زمان اتمام انتقال برای backward compatibility و buildهای 1.2.x حفظ می‌شود. Source of Truth جدید برای ویرایش محتوای Kotlin، `AS-Academy-MainCourse/courses/kotlin/course` است.

پس از sync کامل و اضافه شدن MainCourse provider به build، کپی legacy از Course App حذف خواهد شد. حذف زودهنگام مجاز نیست چون build آفلاین فعلی را می‌شکند.

## MainUi migration

`MainActivity` فعلی موقتاً `AcademyCourseApp` موجود در Core را اجرا می‌کند. این یک compatibility adapter است. وقتی artifact/module قابل build از MainUi آماده شود، launcher به MainUi shell تغییر می‌کند و Core صرفاً engine/API مشترک خواهد بود.

## محتوای جدید

جزوه فارسی توسعه‌یافته Kotlin و مباحث جدید باید ابتدا با Course Package موجود deduplicate شوند. موضوع موجود به درس فعلی افزوده می‌شود؛ فقط موضوع مستقل با Stable ID جدید، درس جدید می‌سازد.
