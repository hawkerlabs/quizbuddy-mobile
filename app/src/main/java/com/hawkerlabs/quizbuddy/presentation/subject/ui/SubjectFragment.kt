package com.hawkerlabs.quizbuddy.presentation.subject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.application.core.ViewModelFactory
import com.hawkerlabs.quizbuddy.databinding.SubjectFragmentBinding

import com.hawkerlabs.quizbuddy.presentation.subject.viewmodel.SubjectsViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.subject_fragment.*
import javax.inject.Inject

class SubjectFragment : DaggerFragment() {

    private lateinit var binding: SubjectFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var subjectsListAdapter: SubjectsListAdapter = SubjectsListAdapter()


    private lateinit var subjectsViewModel: SubjectsViewModel
    private lateinit var courseId: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.subject_fragment, container, false)


        courseId = arguments?.getString("courseId") ?: ""
        return binding.root
    }


    /**
     *
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUi()
        initViewModels()
        subscribeUi()
    }


    private fun initUi() {
        toolbar.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
        list.adapter = subjectsListAdapter
        val mLayoutManager = GridLayoutManager(list.context, 2)

        list.layoutManager = mLayoutManager
        list.itemAnimator = DefaultItemAnimator()


    }

    private fun initViewModels() {

        activity?.let {
            subjectsViewModel =
                ViewModelProvider(it, viewModelFactory).get(SubjectsViewModel::class.java)

        }


    }

    private fun subscribeUi() {
        subjectsViewModel.getSubjects(courseId).observe(viewLifecycleOwner, Observer {
            subjectsListAdapter.onResults(it)
        })


        subjectsViewModel.getLoadState.observe(viewLifecycleOwner, Observer {

            if (!it) {
                listText.visibility = View.VISIBLE
                list.visibility = View.VISIBLE
                progressBarHolder.visibility = View.GONE
            } else {
                listText.visibility = View.GONE
                list.visibility = View.GONE
                progressBarHolder.visibility = View.VISIBLE
            }

        })

    }

}