package com.hawkerlabs.quizbuddy.presentation.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.application.utils.Images
import com.hawkerlabs.quizbuddy.databinding.CategoryFragmentBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.category_fragment.*

class CategoryFragment : DaggerFragment() {

    private lateinit var binding: CategoryFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.category_fragment, container, false)




        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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


        courseImageView.setOnClickListener{
            Navigation.findNavController(binding.root).navigate(R.id.questionFragment)

        }
    }

}