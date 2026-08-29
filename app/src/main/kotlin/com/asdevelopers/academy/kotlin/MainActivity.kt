package com.asdevelopers.academy.kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.asdevelopers.academy.core.ui.AcademyCourseApp

/**
 * نقطه ورود اپ آموزش Kotlin.
 * تمام منطق مشترک آموزش، Navigation، Room، Progress، Search و Quiz در AS Academy Core قرار دارد.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcademyCourseApp(courseId = "kotlin")
        }
    }
}
