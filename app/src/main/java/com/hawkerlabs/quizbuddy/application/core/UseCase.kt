package com.hawkerlabs.quizbuddy.application.core

import io.reactivex.Single

interface UseCase<T>{
    operator fun invoke(): Single<T>
}