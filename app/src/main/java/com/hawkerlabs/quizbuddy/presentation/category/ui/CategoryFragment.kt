package com.hawkerlabs.quizbuddy.presentation.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.application.core.ViewModelFactory
import com.hawkerlabs.quizbuddy.application.utils.Images
import com.hawkerlabs.quizbuddy.databinding.CategoryFragmentBinding
import com.hawkerlabs.quizbuddy.presentation.category.viewmodel.CategoryViewModel
import com.hawkerlabs.quizbuddy.presentation.session.SessionViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.category_fragment.*
import javax.inject.Inject

class CategoryFragment : DaggerFragment() {

    private lateinit var binding: CategoryFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var sessionViewModel: SessionViewModel
    private lateinit var categoryViewModel: CategoryViewModel


    private lateinit var categoriesListAdapter: CategoriesListAdapter

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
        initUi()
        initViewModels()
        subscribeUi()
    }


    /**
     * Fire up the adapter after get categories service call
     */
    private fun subscribeUi() {
        categoryViewModel.getDisplayCategories.observe(
            viewLifecycleOwner,
            Observer { categoriesListItemViewModel ->
                refreshLayout.isRefreshing = false
                categoriesListAdapter = CategoriesListAdapter()
                categoriesListAdapter.onResults(categoriesListItemViewModel)
                list.adapter = categoriesListAdapter

            })



        //On refresh call the viewmodels refresh method which will initiate the service call allover again
        refreshLayout.setOnRefreshListener {
            refreshLayout.isRefreshing = true
            categoryViewModel.onRefresh()
        }


    }

    /**
     *
     */
    private fun initUi() {

        refreshLayout.isRefreshing = true
        binding.collapsingToolbar.title = "Quiz Buddy"
        Glide.with(binding.root.context)
            .asBitmap()
            .load(Images.DISPLAY)
            .centerCrop()
            .into(binding.image)
    }

    private fun initViewModels() {

        activity?.let {
            sessionViewModel =
                ViewModelProvider(it, viewModelFactory).get(SessionViewModel::class.java)
            categoryViewModel =
                ViewModelProvider(it, viewModelFactory).get(CategoryViewModel::class.java)
        }


    }

}