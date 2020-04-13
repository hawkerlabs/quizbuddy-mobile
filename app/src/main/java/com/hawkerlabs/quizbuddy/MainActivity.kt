package com.hawkerlabs.quizbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.hawkerlabs.quizbuddy.application.core.ViewModelFactory
import com.hawkerlabs.quizbuddy.data.QuestionsManager
import com.hawkerlabs.quizbuddy.presentation.session.SessionViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


/**
 * https://help.github.com/en/github/using-git/updating-credentials-from-the-osx-keychain
 */
class MainActivity : DaggerAppCompatActivity() {

    private lateinit var sessionViewModel: SessionViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        QuestionsManager.initialize()

    }
}
