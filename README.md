# AS Academy - Kotlin

مرجع و Course Package اختصاصی آموزش Kotlin در مجموعه AS Academy.

## وضعیت پروژه

این ریپو از صفر بر اساس معماری مشترک AS Academy ساخته می‌شود. منطق و اجزای مشترک اپ آموزشی در `as-academy-core` نگهداری می‌شوند و این ریپو فقط محتوای اختصاصی Kotlin، مثال‌ها، تمرین‌ها، پروژه‌ها، متادیتای دوره و قابلیت‌های ویژه این زبان را نگه می‌دارد.

## مسیر آموزشی

1. **مبانی** - نصب، اولین برنامه، متغیرها، انواع داده، عملگرها، ورودی/خروجی، شرط‌ها، حلقه‌ها، توابع، Null Safety و String.
2. **مقدماتی** - Collections، Lambda، OOP، Data/Enum Classes، Inheritance/Interface، Extensions، Exception Handling، File I/O، Modules و Testing.
3. **پیشرفته** - Generics، Sealed Types، Scope Functions، Delegation، Inline/Reified، Coroutines، Flow، Channels، Gradle Kotlin DSL، DSL Design، Reflection، Java Interop و Performance.
4. **تخصصی** - Android، Jetpack Compose، Ktor، Spring Boot، Kotlin Multiplatform، Compose Multiplatform، Kotlin/JS، Kotlin/Native، Database، Architecture، Security، CI/CD و Production Readiness.

## وابستگی به Core

متعلق به `as-academy-core`: Navigation، Drawer، Profile، Design System، Theme، Database infrastructure، Progress، Bookmark، Search، Quiz/Exercise Engine، Settings، Content Engine، Updater و Course schema.

متعلق به `as-academy-kotlin`: متن درس‌ها، مثال‌های Kotlin، تمرین‌ها و آزمون‌های اختصاصی، پروژه‌ها، Branding، metadata و قابلیت‌های خاص Kotlin.

## ساختار پیشنهادی

```text
as-academy-kotlin/
├── README.md
├── course/
│   ├── course.json
│   ├── basics/
│   ├── beginner/
│   ├── advanced/
│   └── specialist/
├── examples/
├── exercises/
├── projects/
├── assets/
├── docs/
│   ├── course-outline.md
│   ├── authoring-guide.md
│   └── core-integration.md
└── .github/workflows/validate-course.yml
```

## منابع فنی پایه

Kotlin Documentation، Kotlin Getting Started، Kotlin Releases، JetBrains Academy Kotlin Course Template و Kotlin official examples.

نسخه مرجع اولیه دوره بر مبنای Kotlin 2.4.x تنظیم شده است.
