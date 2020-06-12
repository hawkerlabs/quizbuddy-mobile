package com.hawkerlabs.quizbuddy.presentation.course.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.databinding.CoursesListItemBinding
import com.hawkerlabs.quizbuddy.presentation.category.viewmodel.CategoriesListItemViewModel

import com.hawkerlabs.quizbuddy.presentation.course.viewmodel.CoursesListItemViewModel

class CoursesListAdapter : RecyclerView.Adapter<CoursesListAdapter.ViewHolder>(){

    private var courses: List<CoursesListItemViewModel> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: CoursesListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.courses_list_item,
            parent, false
        )
        return ViewHolder(
            binding
        )
    }

    public fun onResults(courses: List<CoursesListItemViewModel>) {
        this.courses = courses
        notifyDataSetChanged()
    }

    /**
     *
     */
    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: CoursesListAdapter.ViewHolder, position: Int) {
        val categoriesListItemViewModel = courses[position]
        holder.bind(categoriesListItemViewModel)
    }

    class ViewHolder(private val binding: CoursesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            coursesListItemViewModel: CoursesListItemViewModel



        ) {


            with(binding) {

              /*  Glide.with(root.context)
                    .asBitmap()
                    .load(categoriesListItemViewModel.image)
                    .centerCrop()
                    .into(binding.categoryImage)

                binding.categoryImage.setOnClickListener {
                    var bundle = bundleOf("categoryId" to categoriesListItemViewModel.id)
                    it.findNavController()
                        .navigate(R.id.questionFragment, bundle)
                }
                viewModel = categoriesListItemViewModel*/
                viewModel = coursesListItemViewModel
            }

        }

    }
}