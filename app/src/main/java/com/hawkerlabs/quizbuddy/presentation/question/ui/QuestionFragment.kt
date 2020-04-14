package com.hawkerlabs.quizbuddy.presentation.question.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.application.core.ViewModelFactory
import com.hawkerlabs.quizbuddy.databinding.QuestionFragmentBinding
import com.hawkerlabs.quizbuddy.presentation.question.viewmodel.QuestionViewModel
import com.hawkerlabs.quizbuddy.presentation.session.SessionViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.question_fragment.*
import javax.inject.Inject


/**
 * https://android--code.blogspot.com/2018/02/android-kotlin-radiogroup-and.html
 */
class QuestionFragment : DaggerFragment(){

//    private lateinit var optionsAdapter: OptionsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var sessionViewModel: SessionViewModel

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


        submitAnswer.setOnClickListener{
            sessionViewModel.onSubmit()
            sessionViewModel.onNext()
        }
    }


    /**
     *
     */
    private fun  subscribeUi(){

        sessionViewModel.getSession.observe(viewLifecycleOwner, Observer {
            val question  = it.currentQuestion
            binding.questionText.text = question.questionText
            binding.optionsGroup.removeAllViews()


            it.currentQuestion.options.map {option->
                val rbn = RadioButton(activity)
                rbn.id = option.id
                rbn.text = option.text
                rbn.typeface = Typeface.create("roboto_medium", Typeface.NORMAL)


                binding.optionsGroup.addView(rbn)
            }

            binding.optionsGroup.setOnCheckedChangeListener(
                RadioGroup.OnCheckedChangeListener { _, checkedId ->

                    sessionViewModel.onOptionSelect(checkedId)

                })
        })


//        sessionViewModel.getQuestion.observe(viewLifecycleOwner, Observer{question->
//            binding.questionText.text = question.questionText
//
//            binding.optionsGroup.removeAllViews()
//            question.options.map {
//                val rbn = RadioButton(activity)
//                rbn.id = it.id
//                rbn.text = it.text
//                rbn.typeface = Typeface.create("roboto_medium", Typeface.NORMAL)
//
//
//                binding.optionsGroup.addView(rbn)
//            }
//
//
//
//            binding.optionsGroup.setOnCheckedChangeListener(
//                RadioGroup.OnCheckedChangeListener { _, checkedId ->
//
//                    sessionViewModel.onOptionSelect(checkedId)
//
//                })
//
//        })


        sessionViewModel.getTestState.observe(viewLifecycleOwner ,Observer{
            if(it){

                Navigation.findNavController(binding.root).navigate(R.id.resultsFragment)
            }
        }

        )

    }

    private fun initViewModels() {

        activity?.let {
            sessionViewModel = ViewModelProvider(it, viewModelFactory).get(SessionViewModel::class.java)



        }


    }

}