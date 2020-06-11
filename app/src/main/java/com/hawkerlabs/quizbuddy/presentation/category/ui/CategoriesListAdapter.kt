package com.hawkerlabs.quizbuddy.presentation.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.databinding.CategoriesListItemBinding
import com.hawkerlabs.quizbuddy.presentation.category.viewmodel.CategoriesListItemViewModel
import com.hawkerlabs.quizbuddy.presentation.result.ui.ResultsFragmentDirections
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class CategoriesListAdapter : RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    private var categories: List<CategoriesListItemViewModel> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: CategoriesListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.categories_list_item,
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
        return categories.size
    }


    /**
     *
     */
    public fun onResults(categories: List<CategoriesListItemViewModel>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoriesListItemViewModel = categories[position]
        holder.bind(categoriesListItemViewModel)
    }


    class ViewHolder(private val binding: CategoriesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            categoriesListItemViewModel: CategoriesListItemViewModel


        ) {

            with(binding) {

                Glide.with(root.context)
                    .asBitmap()
                    .load(categoriesListItemViewModel.image)
                    .centerCrop()
                    .into(binding.categoryImage)

                binding.categoryImage.setOnClickListener {
                    var bundle = bundleOf("categoryId" to categoriesListItemViewModel.id)
                    it.findNavController()
                        .navigate(R.id.questionFragment, bundle)
                }
                viewModel = categoriesListItemViewModel

            }

        }

    }
}