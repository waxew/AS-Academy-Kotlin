# معماری اپ AS Academy Kotlin

این ریپو اکنون علاوه بر Course Package یک Android app واقعی دارد.

- `app/` فقط entry point و تنظیمات اختصاصی Kotlin را نگه می‌دارد.
- `as-academy-core` submodule به ریپوی مرکزی Core اشاره می‌کند.
- در CI محتوای `course/` به assets اپ منتقل می‌شود.
- `AcademyCourseApp("kotlin")` از Core محتوا را بارگذاری می‌کند.
- Room، Progress، Bookmark، Search، Settings، Navigation و Assessment UI در Core هستند.
- تغییر قابلیت مشترک باید در Core انجام شود، نه در app Kotlin.

## جریان کاربر
Home → Level → Chapter → Lesson → Exercise/Quiz → Progress → Project.

داده پیشرفت کاربر در دیتابیس محلی `as_academy_kotlin.db` ذخیره می‌شود و با به‌روزرسانی محتوا از بین نمی‌رود، مشروط به حفظ Stable IDها.
