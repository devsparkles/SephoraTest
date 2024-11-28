package fr.kodesparkle.sephorademo.presentation.products

import androidx.recyclerview.widget.DiffUtil
import fr.kodesparkle.sephorademo.domain.model.ProductWithReviews

class ProductDiffCallback : DiffUtil.ItemCallback<ProductWithReviews>() {
    override fun areItemsTheSame(oldItem: ProductWithReviews, newItem: ProductWithReviews): Boolean {
        return oldItem.product.idProduct == newItem.product.idProduct
    }

    override fun areContentsTheSame(oldItem: ProductWithReviews, newItem: ProductWithReviews): Boolean {
        return oldItem == newItem
    }
}