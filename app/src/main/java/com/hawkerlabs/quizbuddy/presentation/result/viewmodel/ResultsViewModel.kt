package com.hawkerlabs.quizbuddy.presentation.result.viewmodel

import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class ResultsViewModel  @Inject constructor(private val sessionUseCase : SessionUseCase, @Named(
    SCHEDULER_IO) val subscribeOnScheduler: Scheduler,@Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler): ViewModel(){



    }