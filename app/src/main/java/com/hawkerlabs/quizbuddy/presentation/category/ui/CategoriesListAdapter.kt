package com.hawkerlabs.quizbuddy.presentation.category.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.databinding.CategoriesListItemBinding
import com.hawkerlabs.quizbuddy.presentation.category.viewmodel.CategoriesListItemViewModel
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class CategoriesListAdapter :  RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

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
//                    .apply(
//                        bitmapTransform(
//                            RoundedCornersTransformation(
//                                20,
//                                5,
//                                RoundedCornersTransformation.CornerType.ALL
//                            )
//                        )
//                    )
                    .into(binding.categoryImage)
                viewModel = categoriesListItemViewModel

            }

        }

    }
}