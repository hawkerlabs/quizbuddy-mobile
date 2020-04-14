package com.hawkerlabs.quizbuddy.presentation.result.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.application.core.ViewModelFactory
import com.hawkerlabs.quizbuddy.data.QuestionsManager
import com.hawkerlabs.quizbuddy.databinding.QuestionFragmentBinding
import com.hawkerlabs.quizbuddy.databinding.ResultFragmentBinding
import com.hawkerlabs.quizbuddy.presentation.question.viewmodel.QuestionViewModel
import com.hawkerlabs.quizbuddy.presentation.session.SessionViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.category_fragment.*
import kotlinx.android.synthetic.main.result_fragment.*
import javax.inject.Inject

class ResultsFragment : DaggerFragment(){
    private lateinit var binding: ResultFragmentBinding
    private lateinit var sessionViewModel: SessionViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.result_fragment, container, false)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModels()
        initUi()
        binding.resultText.text = "You got " +QuestionsManager.correctAnswerCount + " right"
    }



    private fun initViewModels() {

        activity?.let {
            sessionViewModel = ViewModelProvider(it, viewModelFactory).get(SessionViewModel::class.java)



        }


    }

    private fun initUi(){
        finish.setOnClickListener{
            Navigation.findNavController(binding.root).navigate(R.id.categoryFragment)
        }
    }
}