package com.hawkerlabs.quizbuddy.presentation.subject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.databinding.SubjectsListItemBinding
import com.hawkerlabs.quizbuddy.presentation.category.viewmodel.CategoriesListItemViewModel

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
    public fun onResults(subjects: List<SubjectListItemViewModel>) {
        this.subjects = subjects
        notifyDataSetChanged()
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

                subjectCard.setOnClickListener{
                    var bundle = bundleOf("subjectId" to subjectListItemViewModel.id)
                    it.findNavController()
                        .navigate(R.id.questionFragment, bundle)
                }
                viewModel = subjectListItemViewModel
            }

        }

    }
}
