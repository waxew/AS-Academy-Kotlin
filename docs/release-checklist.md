# Release Checklist — Kotlin 1.2.0

## Course/content gate

- [x] manifest با Course Contract سازگار است
- [x] JSON همه فایل‌ها معتبر است
- [x] Stable ID تکراری وجود ندارد
- [x] lessonId/chapterId/levelIdها معتبرند
- [x] Quizها پاسخ معتبر و explanation دارند
- [x] Exerciseها acceptance criteria دارند
- [x] Projectها skill/deliverable مشخص دارند
- [x] Kotlin reference version روی 2.4.10 تثبیت شده است
- [x] README و CHANGELOG برای 1.2.0 به‌روز شده‌اند
- [x] content version طبق SemVer به 1.2.0 افزایش یافته است
- [x] Course Package کامل در Android assets قرار می‌گیرد
- [x] Solution/Rubric strategy مستند شده است

## Android quality gate

- [x] CI شامل Course validation است
- [x] CI شامل Android lint است
- [x] CI شامل unit test است
- [x] CI شامل Debug APK build است
- [x] CI شامل Release Candidate APK/AAB build است
- [x] CI برای artifactها SHA-256 تولید می‌کند
- [x] app versionName با Course روی 1.2.0 هماهنگ است
- [x] versionCode به مقدار monotonic `12000` افزایش یافته است
- [x] release signing از source code جدا و external شده است

## Publish gate — نیازمند signing identity دائمی

- [ ] permanent release keystore در محیط امن CI فراهم شود
- [ ] signed Publish APK/AAB با همان identity ساخته شود
- [ ] امضای artifact نهایی با `apksigner verify --verbose --print-certs` بررسی شود
- [ ] SHA-256 نهایی signed artifact ثبت شود
- [ ] تست نصب/upgrade روی دستگاه یا emulator واقعی انجام شود

سه مورد آخر عمداً قبل از دریافت signing identity دائمی بسته نمی‌شوند. استفاده از کلید موقت برای سبزکردن checklist ممنوع است، چون update compatibility نسخه‌های آینده را از بین می‌برد.
