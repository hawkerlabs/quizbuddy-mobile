package com.hawkerlabs.quizbuddy.presentation.subject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.databinding.SubjectFragmentBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.subject_fragment.*

class SubjectFragment : DaggerFragment() {

    private lateinit var binding: SubjectFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.subject_fragment, container, false)



        return binding.root
    }


    /**
     *
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
    }

}