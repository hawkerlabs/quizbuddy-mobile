package com.hawkerlabs.quizbuddy.presentation.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.application.core.ViewModelFactory
import com.hawkerlabs.quizbuddy.application.utils.Images
import com.hawkerlabs.quizbuddy.databinding.CategoryFragmentBinding
import com.hawkerlabs.quizbuddy.presentation.category.viewmodel.CategoryViewModel
import com.hawkerlabs.quizbuddy.presentation.course.ui.CoursesListAdapter
import com.hawkerlabs.quizbuddy.presentation.result.ui.ResultsFragmentDirections
import com.hawkerlabs.quizbuddy.presentation.session.SessionViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.category_fragment.*
import javax.inject.Inject


/**
 * The care
 */
class CategoryFragment : DaggerFragment() {

    private lateinit var binding: CategoryFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var sessionViewModel: SessionViewModel
    private lateinit var categoryViewModel: CategoryViewModel


    private lateinit var categoriesListAdapter: CategoriesListAdapter
    private lateinit var coursesListAdapter: CoursesListAdapter


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
        initViewModels()
        subscribeUi()
    }


    /**
     *
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            activity?.finish()
        }
    }


    /**
     * Fire up the adapter after get categories service call
     */
    private fun subscribeUi() {
        categoryViewModel.getPageState().observe(viewLifecycleOwner, Observer {

            refreshLayout.isRefreshing = false
            categoriesListAdapter = CategoriesListAdapter()
            categoriesListAdapter.onResults(it.categories)
            list.adapter = categoriesListAdapter


            coursesListAdapter = CoursesListAdapter()
            coursesListAdapter.onResults(it.courses)
            newCourseRecyclerView.adapter = coursesListAdapter

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
            .load(Images.DISPLAY).fitCenter()
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