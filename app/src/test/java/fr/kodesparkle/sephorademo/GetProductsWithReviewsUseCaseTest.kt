import fr.kodesparkle.sephorademo.domain.model.Product
import fr.kodesparkle.sephorademo.domain.model.ProductReview
import fr.kodesparkle.sephorademo.domain.model.Review
import fr.kodesparkle.sephorademo.domain.repository.RemoteProductRepository
import fr.kodesparkle.sephorademo.domain.repository.RemoteReviewRepository
import fr.kodesparkle.sephorademo.domain.usecases.GetProductsWithReviewsUseCase
import fr.kodesparkle.sephorademo.domain.util.NetworkError
import fr.kodesparkle.sephorademo.domain.util.Result
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals

// Test class
class GetProductsWithReviewsUseCaseTest {

    // Mocks
    private lateinit var productRepository: RemoteProductRepository
    private lateinit var reviewRepository: RemoteReviewRepository
    private lateinit var getProductsWithReviewsUseCase: GetProductsWithReviewsUseCase

    @Before
    fun setUp() {
        // Initialisation des mocks
        productRepository = mock(RemoteProductRepository::class.java)
        reviewRepository = mock(RemoteReviewRepository::class.java)

        // Initialisation du cas d'utilisation avec les mocks
        getProductsWithReviewsUseCase =
            GetProductsWithReviewsUseCase(productRepository, reviewRepository)
    }

    @Test
    fun `should calculate average rating correctly when reviews are present`() = runBlocking {
        // Données simulées pour les produits et les critiques
        val products = listOf(
            Product(
                idProduct = 1,
                name = "Product A",
                averageRating = 0.0,
                description = "",
                price = 10.0,
                imageUrlSmall = "",
                imageUrlLarge = ""
            ),
            Product(
                idProduct = 2,
                name = "Product B",
                averageRating = 0.0,
                description = "",
                price = 20.0,
                imageUrlSmall = "",
                imageUrlLarge = ""
            )
        )

        val reviews = listOf(
            ProductReview(
                productId = 1,
                reviews = listOf(Review("Review A", "",4.0), Review("Review B", "",5.0))
            ),
            ProductReview(
                productId = 2,
                reviews = listOf(Review("Review C", "",3.0), Review("Review D","",4.0))
            )
        )

        // Simuler le retour des repositories
        `when`(productRepository.getProducts()).thenReturn(Result.Success(products))
        `when`(reviewRepository.getReviews()).thenReturn(Result.Success(reviews))

        // Exécuter le cas d'utilisation
        val result = getProductsWithReviewsUseCase()

        // Vérifier le résultat
        assert(result is Result.Success)
        val productsWithReviews = (result as Result.Success).data

        // Vérifier que les notes moyennes sont correctement calculées
        val productWithReviewA = productsWithReviews.find { it.product.idProduct.toInt() == 1 }
        assertEquals(4.5, productWithReviewA?.averageRating)

        val productWithReviewB = productsWithReviews.find { it.product.idProduct.toInt() == 2 }
        assertEquals(3.5, productWithReviewB?.averageRating)
    }

    @Test
    fun `should return error if product repository fails`() = runBlocking {
        // Simuler une erreur du repository des produits
        `when`(productRepository.getProducts()).thenReturn(Result.Error(NetworkError.REQUEST_TIMEOUT))
        `when`(reviewRepository.getReviews()).thenReturn(Result.Success(emptyList()))

        // Exécuter le cas d'utilisation
        val result = getProductsWithReviewsUseCase()

        // Vérifier que le résultat est une erreur
        assert(result is Result.Error)
        assertEquals(NetworkError.REQUEST_TIMEOUT, (result as Result.Error).error)
    }

    @Test
    fun `should return error if review repository fails`() = runBlocking {
        // Simuler une erreur du repository des critiques
        `when`(productRepository.getProducts()).thenReturn(Result.Success(emptyList()))
        `when`(reviewRepository.getReviews()).thenReturn(Result.Error(NetworkError.SERVER_ERROR))

        // Exécuter le cas d'utilisation
        val result = getProductsWithReviewsUseCase()

        // Vérifier que le résultat est une erreur
        assert(result is Result.Error)
        assertEquals(NetworkError.SERVER_ERROR, (result as Result.Error).error)
    }
}
