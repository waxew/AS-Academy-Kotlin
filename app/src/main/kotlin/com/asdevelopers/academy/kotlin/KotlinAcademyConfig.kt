package com.asdevelopers.academy.kotlin

/**
 * تنها configuration اختصاصی Course App Kotlin.
 *
 * محتوای آموزشی در AS-Academy-MainCourse، UI مشترک در AS-Academy-MainUi
 * و engineهای مشترک در AS-Academy-Core نگهداری می‌شوند. این object عمداً
 * کوچک است تا Course App به محل تکرار منطق یا محتوا تبدیل نشود.
 */
object KotlinAcademyConfig {
    const val COURSE_ID = "kotlin"
    const val TITLE_FA = "آموزش جامع Kotlin"
    const val TITLE_EN = "Kotlin Complete Course"
    const val CONTENT_SOURCE = "AS-Academy-MainCourse/courses/kotlin/course"

    /**
     * در دوره Kotlin اجرای مثال کد، تمرین، Quiz، پروژه و Glossary فعال است.
     * MainUi این capabilityها را از Course Package نهایی نیز دریافت خواهد کرد.
     */
    val capabilities = setOf(
        "codeRunner",
        "terminalExamples",
        "diagrams",
        "quizzes",
        "exercises",
        "projects",
        "glossary"
    )
}
