package com.hawkerlabs.quizbuddy.presentation.result.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.databinding.ResultsListItemBinding
import com.hawkerlabs.quizbuddy.presentation.result.viewmodel.ResultsListItemViewModel

class ResultsAdapter : RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {

    private var results: List<ResultsListItemViewModel> = emptyList()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ResultsListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.results_list_item,
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
        return results.size
    }


    /**
     *
     */
    public fun onResults(results: List<ResultsListItemViewModel>) {
        this.results = results
        notifyDataSetChanged()
    }


    /**
     *
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultsListItemViewModel = results[position]
        holder.bind(resultsListItemViewModel)
    }


    class ViewHolder(private val binding: ResultsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(

            resultsListItemViewModel: ResultsListItemViewModel

        ) {

            with(binding) {

                viewModel = resultsListItemViewModel

            }
        }

    }
}


