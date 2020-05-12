package com.hawkerlabs.quizbuddy.presentation.category.viewmodel

import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.data.api.model.Category


/**
 *
 */
class CategoriesListItemViewModel (category : Category) : ViewModel(){
    val id = category.id
    val name = category.name
    val image = category.image
    val isVisible = category.isVisible

}