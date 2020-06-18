package com.hawkerlabs.quizbuddy.presentation.course.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        val coursesListItemViewModel = courses[position]
        holder.bind(coursesListItemViewModel)
    }

    class ViewHolder(private val binding: CoursesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            coursesListItemViewModel: CoursesListItemViewModel



        ) {


            with(binding) {
                Glide.with(root.context)
                    .asBitmap()
                    .load("https://d1z8nu24f2lody.cloudfront.net/courses/course_cbse.jpg")
                    .centerCrop()
                    .into(binding.itemImageView)




                binding.courseCard.setOnClickListener {
                    var bundle = bundleOf("courseId" to coursesListItemViewModel.id)
                    it.findNavController()
                        .navigate(R.id.subjectFragment, bundle)
                }
                viewModel = coursesListItemViewModel
            }

        }

    }
}