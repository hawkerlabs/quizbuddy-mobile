package com.hawkerlabs.quizbuddy.presentation.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.application.core.ViewModelFactory
import com.hawkerlabs.quizbuddy.application.utils.Images
import com.hawkerlabs.quizbuddy.databinding.CategoryFragmentBinding
import com.hawkerlabs.quizbuddy.presentation.session.SessionViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.category_fragment.*
import javax.inject.Inject

class CategoryFragment : DaggerFragment() {

    private lateinit var binding: CategoryFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var sessionViewModel: SessionViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.category_fragment, container, false)




        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModels()
        initUi()

    }


    /**
     *
     */
    private fun initUi(){
        Glide.with(binding.root.context)
            .asBitmap()
            .load(Images.CATEGORIES)
            .centerCrop()
            .into(binding.courseImageView)


        world_knowledge_view.setOnClickListener{
            Navigation.findNavController(binding.root).navigate(R.id.questionFragment)

        }
    }

    private fun initViewModels() {

        activity?.let {
            sessionViewModel = ViewModelProvider(it, viewModelFactory).get(SessionViewModel::class.java)
     }


    }

}