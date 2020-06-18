package com.hawkerlabs.quizbuddy.presentation.subject.viewmodel

import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.data.model.Subject

class SubjectListItemViewModel(subject : Subject) : ViewModel(){
    val id = subject.id
    val title = subject.title
    val course = subject.course

}
