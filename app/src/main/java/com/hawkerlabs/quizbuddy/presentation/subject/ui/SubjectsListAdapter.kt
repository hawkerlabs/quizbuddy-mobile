package com.hawkerlabs.quizbuddy.presentation.subject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.databinding.SubjectsListItemBinding

import com.hawkerlabs.quizbuddy.presentation.subject.viewmodel.SubjectListItemViewModel

class SubjectsListAdapter : RecyclerView.Adapter<SubjectsListAdapter.ViewHolder>() {
    private var subjects: List<SubjectListItemViewModel> = emptyList()


    /**
     *
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: SubjectsListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.subjects_list_item,
            parent, false
        )
        return ViewHolder(
            binding
        )
    }


    /**
     *
     */
    override fun getItemCount(): Int {
        return subjects.size
    }


    /**
     *
     */
    override fun onBindViewHolder(holder: SubjectsListAdapter.ViewHolder, position: Int) {
        val subjectListItemViewModel = subjects[position]
        holder.bind(subjectListItemViewModel)
    }


    class ViewHolder(private val binding: SubjectsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            subjectListItemViewModel: SubjectListItemViewModel


        ) {


            with(binding) {


//                binding.courseCard.setOnClickListener {
//                    var bundle = bundleOf("courseId" to coursesListItemViewModel.id)
//                    it.findNavController()
//                        .navigate(R.id.subjectFragment, bundle)
//                }
                viewModel = subjectListItemViewModel
            }

        }

    }
}
