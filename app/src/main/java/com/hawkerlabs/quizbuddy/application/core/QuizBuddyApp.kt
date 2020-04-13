package com.hawkerlabs.quizbuddy.application.core

import com.hawkerlabs.quizbuddy.application.core.dagger.component.DaggerAppComponent
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
