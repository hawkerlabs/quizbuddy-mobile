package com.hawkerlabs.quizbuddy.presentation.question.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.data.model.Option
import com.hawkerlabs.quizbuddy.databinding.OptionsListItemBinding
import com.hawkerlabs.quizbuddy.presentation.question.viewmodel.OptionsListItemViewModel

class OptionsAdapter() : RecyclerView.Adapter<OptionsAdapter.ViewHolder>(){
    private var optionViewModels = mutableListOf<OptionsListItemViewModel>()




    /**
     *
     */
    public fun setOptions(options: Set<Option>) {
//         var viewModelList = mutableListOf<OptionsListItemViewModel>()
//
//        optionViewModels = viewModelList
        optionViewModels.clear()


        options.forEach { option ->
            optionViewModels.add(OptionsListItemViewModel(option))
        }

        notifyDataSetChanged()
    }

    /**
     *
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OptionsAdapter.ViewHolder {
        val binding: OptionsListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.options_list_item,
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
        return optionViewModels.size
    }


    /**
     *
     */
    override fun onBindViewHolder(holder: OptionsAdapter.ViewHolder, position: Int) {
        val optionsListItemViewModel = optionViewModels[position]
        holder.bind(optionsListItemViewModel)
    }



    class ViewHolder(private val binding: OptionsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(

            optionsListItemViewModel: OptionsListItemViewModel

        ) {

            with(binding) {

                viewModel = optionsListItemViewModel
//                val direction =
//                    PeopleFragmentDirections.actionPeopleFragmentToPeopleDetailsFragment()
//                binding.mainLayout.setOnClickListener {
//
//
//                    var bundle = bundleOf("url" to peopleListItemViewModel.url)
//                    it.findNavController()
//                        .navigate(R.id.action_peopleFragment_to_peopleDetailsFragment, bundle)
//                }
            }

        }

    }

}