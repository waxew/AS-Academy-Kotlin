package com.asdevelopers.academy.kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.asdevelopers.academy.core.ui.AcademyCourseApp

/**
 * نقطه ورود بسیار نازک اپ Kotlin در معماری جدید AS Academy.
 *
 * Core مالک engine و persistence است، MainCourse مالک محتوای آموزشی است و
 * MainUi مالک پوسته مشترک خواهد بود. تا زمان انتشار module قابل build از MainUi،
 * AcademyCourseApp موجود در Core به‌عنوان compatibility shell استفاده می‌شود.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcademyCourseApp(courseId = KotlinAcademyConfig.COURSE_ID)
        }
    }
}
