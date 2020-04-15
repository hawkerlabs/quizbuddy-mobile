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
 *  adb shell dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'
 *
 * https://antonioleiva.com/clean-architecture-android/
 *
 * https://rubygarage.org/blog/clean-android-architecture
 * https://proandroiddev.com/android-clean-architecture-with-viewmodel-usecases-and-repositories-part-1-b9e63889a1aa
 *
 * https://github.com/android/architecture-components-samples
 *
 * https://proandroiddev.com/rxjava-2-parallel-multiple-network-call-made-easy-1e1f14163eef
 *
 *  https://www.zoftino.com/passing-data-between-android-fragments-using-viewmodel
 *
 *  https://medium.com/databinding-ktx/databindingutil-vs-databinding-ktx-5c0c04a5c483
 *
 *  https://proandroiddev.com/when-to-load-data-in-viewmodels-ad9616940da7
 *
 *  https://thoughtbot.com/blog/finite-state-machines-android-kotlin-good-times#defining-our-finite-state-machine
 *
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
