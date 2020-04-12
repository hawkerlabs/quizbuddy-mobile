package com.hawkerlabs.quizbuddy.core

import com.hawkerlabs.quizbuddy.core.dagger.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class QuizBuddyApp : DaggerApplication(){
    override fun onCreate() {
        super.onCreate()





    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder().application(this).build()
    }
}
