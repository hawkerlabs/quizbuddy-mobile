package com.hawkerlabs.quizbuddy.presentation.result.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.application.core.ViewModelFactory
import com.hawkerlabs.quizbuddy.application.utils.Images
import com.hawkerlabs.quizbuddy.data.model.CurrentOption
import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.databinding.ResultFragmentBinding
import com.hawkerlabs.quizbuddy.presentation.result.viewmodel.ResultsListItemViewModel
import com.hawkerlabs.quizbuddy.presentation.result.viewmodel.ResultsViewModel
import com.hawkerlabs.quizbuddy.presentation.session.SessionViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.question_fragment.view.*
import kotlinx.android.synthetic.main.result_fragment.*
import javax.inject.Inject

class ResultsFragment : DaggerFragment() {
    private lateinit var binding: ResultFragmentBinding
    private lateinit var sessionViewModel: SessionViewModel
    private lateinit var resultsViewModel: ResultsViewModel

    private var resultsAdapter = ResultsAdapter()

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


    /**
     *
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModels()
        initUi()
        subscribeUi()


    }

    /**
     * On back pressed goto the categories fragment
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Navigation.findNavController(binding.root).navigate(ResultsFragmentDirections.actionResultsFragmentToCategoryFragment())
        }
    }



//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                Navigation.findNavController(binding.root).navigate(R.id.categoryFragment)
//            }
//        })
//    }


    /**
     *
     */
    private fun subscribeUi() {

        sessionViewModel.getResult.observe(viewLifecycleOwner, Observer { it ->
            var results = mutableListOf<ResultsListItemViewModel>()
            it.inCorrectAnswers.map { question ->
                var option = question.options.filter { option ->
                    option.isCorrectAnswer == true
                }
                option[0].text?.let { value ->
                    ResultsListItemViewModel(
                        question.questionText,
                        value
                    )
                }?.let { it2 -> results.add(it2) }

            }
            resultsAdapter.onResults(results)
            resultsViewModel.onResults(it)


        })


        /**
         *
         */
        resultsViewModel.getScore.observe(viewLifecycleOwner, Observer { score ->

            binding.score.text = score.scorePercentage
        })

    }

    private fun initViewModels() {

        activity?.let {
            sessionViewModel =
                ViewModelProvider(it, viewModelFactory).get(SessionViewModel::class.java)
            resultsViewModel =
                ViewModelProvider(it, viewModelFactory).get(ResultsViewModel::class.java)

        }


    }

    private fun initUi() {
        list.adapter = resultsAdapter

        binding.collapsingToolbar.title = "Test Results"
        Glide.with(binding.root.context)
            .asBitmap()
            .load(Images.FINISH).fitCenter()
            .into(binding.image)

        binding.finish.setOnClickListener {
            sessionViewModel.onFinishTest()
//            Navigation.findNavController(binding.root).navigate(R.id.categoryFragment)


            Navigation.findNavController(binding.root).navigate(R.id.categoryFragment)



        }
    }
}