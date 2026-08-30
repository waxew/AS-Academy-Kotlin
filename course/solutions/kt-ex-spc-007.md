# Reviewer rubric — kt-ex-spc-007

## Architecture

- dependency direction مشخص و بدون cycle است.
- domain policy به framework وابسته نیست مگر با دلیل مستند.
- I/O boundaryها و ownership state مشخص‌اند.

## Security

نمونه threatها: credential leakage، broken authorization/IDOR، injection، dependency compromise، replay/duplicate request، sensitive logging.

برای هر threat باید asset، attack path، impact، mitigation و روش verification ثبت شود. secret نباید در source، artifact یا log قرار گیرد.

## CI quality gates

حداقل pipeline: package validation → compile → unit tests → integration tests → lint/static analysis → security/dependency checks → release build → checksum/provenance metadata.

## Observability

یک SLO نمونه: 99.9% درخواست‌های معتبر در پنجره ماهانه موفق باشند. حداقل metrics: request latency distribution، error rate، saturation/queue depth. log ساختاریافته باید correlation ID داشته باشد و trace باید boundaryهای مهم را دنبال کند.

## Recovery

- backup بدون restore test کافی نیست.
- migration باید rollback یا forward-fix strategy داشته باشد.
- release باید stop criteria و post-release verification داشته باشد.
- incident runbook باید owner، detection signal، mitigation و recovery verification را مشخص کند.

پاسخ دانشجو زمانی production-ready محسوب می‌شود که evidence قابل بررسی ارائه کند، نه صرفاً فهرست ابزارها.
