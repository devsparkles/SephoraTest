package fr.kodesparkle.sephorademo

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.kodesparkle.sephorademo.presentation.products.ProductAdapter
import fr.kodesparkle.sephorademo.presentation.products.ProductEvent
import fr.kodesparkle.sephorademo.presentation.products.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val productViewModel: ProductViewModel by viewModel()


    private lateinit var productsAdapter: ProductAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        // Set up RecyclerView
        productsAdapter = ProductAdapter { productId ->
            productViewModel.toggleReviewVisibility(productId)
        }
        recyclerView.adapter = productsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe ViewModel state
        lifecycleScope.launchWhenStarted {
            productViewModel.state.collect { state ->
                productsAdapter.submitList(state.products)
                // Handle loading state or errors if necessary
            }
        }

        // Observe ViewModel events
        lifecycleScope.launchWhenStarted {
            productViewModel.events.collect { event ->
                when (event) {
                    is ProductEvent.Error -> showError(event.error.toString())
                }
            }
        }

        // Search functionality
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productViewModel.onSearch(newText ?: "")
                return true
            }
        })


    }

    private fun showError(message: String?) {
        Toast.makeText(this, message ?: "An error occurred", Toast.LENGTH_SHORT).show()
    }
}
