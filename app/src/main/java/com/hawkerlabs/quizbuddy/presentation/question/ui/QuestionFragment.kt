package com.hawkerlabs.quizbuddy.presentation.question.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.core.dagger.ViewModelFactory
import com.hawkerlabs.quizbuddy.databinding.QuestionFragmentBinding
import com.hawkerlabs.quizbuddy.presentation.question.viewmodel.QuestionViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class QuestionFragment : DaggerFragment(){

    private lateinit var optionsAdapter: OptionsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var questionViewModel: QuestionViewModel


    private lateinit var binding: QuestionFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.question_fragment, container, false)



        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModels()
        initUi()
        subscribeUi()

    }

    private fun initUi() {
        optionsAdapter = OptionsAdapter()
    }


    /**
     *
     */
    private fun  subscribeUi(){
        questionViewModel.getQuestion.observe(viewLifecycleOwner, Observer{question->
            binding.questionText.text = question.questionText

            binding.optionsList.adapter = optionsAdapter
            optionsAdapter.setOptions(question.options)

        })

    }

    private fun initViewModels() {

        activity?.let {
            questionViewModel = ViewModelProvider(it, viewModelFactory).get(QuestionViewModel::class.java)
        }


    }

}