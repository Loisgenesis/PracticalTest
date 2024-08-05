package com.genesis.practicaltest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.genesis.practicaltest.api.ApiService
import com.genesis.practicaltest.model.CategoriesResponse
import com.genesis.practicaltest.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CategoryViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var viewModel: CategoryViewModel

    @Mock
    private lateinit var categoriesObserver: Observer<List<Category>>

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Mock
    private lateinit var errorObserver: Observer<String?>

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = CategoryViewModel()
       // apiService = ApiService.create()
        viewModel.categories.observeForever(categoriesObserver)
        viewModel.loading.observeForever(loadingObserver)
        viewModel.error.observeForever(errorObserver)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        viewModel.categories.removeObserver(categoriesObserver)
        viewModel.loading.removeObserver(loadingObserver)
        viewModel.error.removeObserver(errorObserver)
    }


    //Test passes successfully when categories are returned
    @Test
    fun fetchCategories_success() = runTest {
        val mockCategories = listOf(
            Category("1", "Beef", "https://www.themealdb.com/images/category/beef.png", "Beef description")
        )
        val mockResponse = CategoriesResponse(mockCategories)

        `when`(apiService.getCategories()).thenReturn(mockResponse)

        viewModel.fetchCategories()

        advanceUntilIdle()

        // Verifying observer interactions
        val inOrder = inOrder(loadingObserver, categoriesObserver, errorObserver)
        inOrder.verify(loadingObserver).onChanged(true) // Loading started
        inOrder.verify(categoriesObserver).onChanged(mockCategories) // Data loaded
        inOrder.verify(loadingObserver).onChanged(false) // Loading ended
        inOrder.verify(errorObserver).onChanged(null) // No error

    }

    //Test passes successfully when error is thrown
    @Test
    fun fetchCategories_failure() = runTest {
        val errorMessage = "Error occurred"

        `when`(apiService.getCategories()).thenThrow(RuntimeException(errorMessage))

        viewModel.fetchCategories()

        advanceUntilIdle()

        // Verifying observer interactions
        val inOrder = inOrder(loadingObserver, errorObserver)
        inOrder.verify(loadingObserver).onChanged(true) // Loading started
        inOrder.verify(errorObserver).onChanged("Error occurred: $errorMessage") // Error occurred
        inOrder.verify(loadingObserver).onChanged(false) // Loading ended
    }
}

