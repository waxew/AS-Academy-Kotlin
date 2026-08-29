# Release Runbook

1. `tools/validate_course.py` اجرا شود.
2. GitHub Actions سبز باشد.
3. Stable ID جدید با ID منتشرشده تداخل نداشته باشد.
4. نسخه `manifest.json` با SemVer افزایش یابد.
5. CHANGELOG به‌روز شود.
6. package روی نسخه minimum Core آزمایش شود.
7. migration داده/پیشرفت کاربر در صورت تغییر schema بررسی شود.
8. release candidate بازبینی شود.
9. پس از انتشار، smoke test و rollback criteria بررسی شود.
