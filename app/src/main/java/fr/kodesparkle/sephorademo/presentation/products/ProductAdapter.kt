package fr.kodesparkle.sephorademo.presentation.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.kodesparkle.sephorademo.R
import fr.kodesparkle.sephorademo.domain.model.ProductWithReviews
import java.util.Locale

class ProductAdapter(
    private val onToggleReviews: (Int) -> Unit
) : ListAdapter<ProductWithReviews, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productWithReviews = getItem(position) // Use `getItem` provided by ListAdapter
        holder.bind(productWithReviews)

        holder.toggleReviewsButton.setOnClickListener {
            onToggleReviews(productWithReviews.product.idProduct.toInt())
        }
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productName: TextView = itemView.findViewById(R.id.productName)
        private val productRating: TextView = itemView.findViewById(R.id.productRating)
        val toggleReviewsButton: Button = itemView.findViewById(R.id.toggleReviewsButton)
        private val reviewsLayout: LinearLayout = itemView.findViewById(R.id.reviewsLayout)

        fun bind(productWithReviews: ProductWithReviews) {
            productName.text = productWithReviews.product.name
            productRating.text =
                String.format(Locale.getDefault(), "%.2f", productWithReviews.averageRating)
            reviewsLayout.removeAllViews()

            if (productWithReviews.isExpanded) {
                reviewsLayout.visibility = View.VISIBLE
                productWithReviews.reviews.forEach { review ->
                    val reviewView = LayoutInflater.from(itemView.context)
                        .inflate(R.layout.item_review, reviewsLayout, false) as TextView
                    reviewView.text =
                        reviewView.context.getString(R.string.rating, review.rating, review.text)
                    reviewsLayout.addView(reviewView)
                }
                toggleReviewsButton.text =
                    toggleReviewsButton.context.getString(R.string.hide_reviews)
            } else {
                reviewsLayout.visibility = View.GONE
                toggleReviewsButton.text =
                    toggleReviewsButton.context.getString(R.string.show_reviews)
            }
        }
    }
}

