package com.hawkerlabs.quizbuddy.presentation.question.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.core.dagger.ViewModelFactory
import com.hawkerlabs.quizbuddy.databinding.QuestionFragmentBinding
import com.hawkerlabs.quizbuddy.presentation.question.viewmodel.OptionsListItemViewModel
import com.hawkerlabs.quizbuddy.presentation.question.viewmodel.QuestionViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.question_fragment.*
import javax.inject.Inject


class QuestionFragment : DaggerFragment(){

//    private lateinit var optionsAdapter: OptionsAdapter

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
//        optionsAdapter = OptionsAdapter()
        binding.optionsGroup.orientation = LinearLayout.HORIZONTAL;
        val rbn = RadioButton(activity)

        rbn.id = View.generateViewId()
        rbn.text = "it.text"

       var  params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        binding.optionsGroup.layoutParams = params
        binding.optionsGroup.addView(rbn)
        binding.optionsGroup.addView(rbn)
    }


    /**
     *
     */
    private fun  subscribeUi(){
        val rbn = RadioButton(activity)
        questionViewModel.getQuestion.observe(viewLifecycleOwner, Observer{question->
            binding.questionText.text = question.questionText


//            question.options.map {
//                rbn.id = View.generateViewId()
//                rbn.text = it.text
//
//                binding.optionsGroup.addView(rbn)
//            }
//            binding.optionsList.adapter = optionsAdapter
//            optionsAdapter.setOptions(question.options)

        })

    }

    private fun initViewModels() {

        activity?.let {
            questionViewModel = ViewModelProvider(it, viewModelFactory).get(QuestionViewModel::class.java)
        }


    }

}