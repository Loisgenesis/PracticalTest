package com.genesis.practicaltest.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genesis.practicaltest.model.Category
import com.genesis.practicaltest.api.ApiService
import kotlinx.coroutines.launch

class CategoryViewModel(private  val apiService: ApiService= ApiService.create()) : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: MutableLiveData<String?> get() = _error


    init {
        fetchCategories()
    }

    fun fetchCategories() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = apiService.getCategories()
                _loading.value = false
                if (response.categories.isEmpty()) {
                    _error.value = "Category List is empty, please try again !!"
                } else {
                    _categories.value = response.categories
                    _error.value = null
                }

            } catch (e: Exception) {
                println("Error from api is $e")
                _error.value = "Error occurred: ${e.message}"
                _loading.value = false
            }
        }
    }
}