package com.asdevelopers.academy.kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.asdevelopers.academy.mainui.AcademyMainUi

/**
 * نقطه ورود نازک اپ Kotlin در معماری جدید AS Academy.
 *
 * Core مالک engine و persistence، MainCourse مالک Course Package و MainUi مالک
 * presentation/navigation مشترک است. این Activity فقط هویت دوره را تزریق می‌کند.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcademyMainUi(courseId = KotlinAcademyConfig.COURSE_ID)
        }
    }
}
