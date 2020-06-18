package com.hawkerlabs.quizbuddy.presentation.course.viewmodel

import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.data.model.Course

class  CoursesListItemViewModel(course : Course) : ViewModel(){
    val id = course.id
    val name = course.name
    val description = course.description
    val isVisible = course.isVisible
    val classes = course.classes
}
