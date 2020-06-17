package com.hawkerlabs.quizbuddy.presentation.question.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.application.core.ViewModelFactory
import com.hawkerlabs.quizbuddy.databinding.QuestionFragmentBinding
import com.hawkerlabs.quizbuddy.presentation.session.SessionViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.question_fragment.*
import javax.inject.Inject


/**
 * https://android--code.blogspot.com/2018/02/android-kotlin-radiogroup-and.html
 */
class QuestionFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var sessionViewModel: SessionViewModel


    private lateinit var categoryId: String

    private lateinit var binding: QuestionFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.question_fragment, container, false)


        categoryId = arguments?.getString("categoryId") ?: ""
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModels()
        initUi()
        subscribeUi()

    }

    private fun initUi() {
        binding.progressBarHolder.visibility = View.VISIBLE
        binding.questionLayout.visibility = View.GONE

        next.setOnClickListener {
            sessionViewModel.onSubmit()
            sessionViewModel.onNext()
        }


        previous.setOnClickListener {
            sessionViewModel.onPrevious()

        }
    }


    /**
     *
     */
    private fun subscribeUi() {

        sessionViewModel.getCurrentQuestion.observe(viewLifecycleOwner, Observer {

            if (it != null) {
                binding.progressBarHolder.visibility = View.GONE
                binding.questionLayout.visibility = View.VISIBLE

                binding.questionText.text = it.questionText
                binding.optionsGroup.removeAllViews()


                it.options.map { option ->
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
            }


        })


        sessionViewModel.sessionState.observe(viewLifecycleOwner, Observer { sessionState ->
            when (sessionState) {
                SessionViewModel.SessionState.FIRST_QUESTION -> binding.previous.isEnabled = false
                SessionViewModel.SessionState.ACTIVE_STATE -> binding.previous.isEnabled = true
            }
        })



        sessionViewModel.currentIndex.observe(viewLifecycleOwner, Observer {
            binding.questionNo.text = it
        })


        sessionViewModel.isTestFinished.observe(viewLifecycleOwner, Observer {
            if (it) {

                Navigation.findNavController(binding.root).navigate(R.id.resultsFragment)
            }
        }

        )

    }


    /**
     * If the user clicks back in an active test session show a dialog
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            showAlertDialog()
        }
    }

    /**
     * Show alert on back pressed
     */
    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this.requireContext())
        builder.setTitle(R.string.ALERT_TITLE)
        builder.setMessage(R.string.ALERT_TEXT)
        builder.setPositiveButton(
            R.string.CONTINUE
        ) { _, _ -> Navigation.findNavController(binding.root).popBackStack() }
        builder.setNegativeButton(R.string.CANCEL, null)
        builder.show()
    }


    private fun initViewModels() {

        activity?.let {
            sessionViewModel =
                ViewModelProvider(it, viewModelFactory).get(SessionViewModel::class.java)


        }
        sessionViewModel.onNewSession(categoryId)


    }

}