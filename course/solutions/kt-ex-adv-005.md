# Solution blueprint — kt-ex-adv-005

این پاسخ یک معماری مرجع است؛ نام ماژول‌ها می‌تواند با محصول واقعی تغییر کند.

```text
:app
  -> :feature:catalog
  -> :feature:cart
  -> :feature:checkout

:feature:catalog -> :domain:catalog
:feature:cart -> :domain:cart
:feature:checkout -> :domain:checkout -> :domain:cart

:data:catalog -> :domain:catalog
:data:cart -> :domain:cart
:data:checkout -> :domain:checkout
```

قواعد مرجع:

- `:app` محل Composition Root است و implementationها را به interfaceهای domain متصل می‌کند.
- featureها نباید implementation دیتای feature دیگر را import کنند.
- `api` فقط وقتی استفاده می‌شود که type وابستگی بخشی از public API ماژول جاری باشد؛ در غیر این صورت `implementation`.
- cycle بین moduleها ممنوع است. dependency direction باید از UI/feature به abstractionهای domain و سپس wiring در composition root کنترل شود.
- build-logic مشترک باید در convention plugin قرار گیرد تا تنظیمات Android/Kotlin در moduleها کپی نشود.
- یک verification task یا architecture test باید dependencyهای ممنوع را در CI رد کند.

معیار ارزیابی اصلی، تعداد module بیشتر نیست؛ استقلال تغییر، API surface کوچک، build قابل اندازه‌گیری و dependency graph قابل توضیح است.
