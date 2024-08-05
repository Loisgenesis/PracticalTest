package com.genesis.practicaltest.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genesis.practicaltest.database.Graph
import com.genesis.practicaltest.database.User
import com.genesis.practicaltest.database.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository = Graph.userRepository
) : ViewModel() {

    var userNameState by mutableStateOf("")
    var userAgeState by mutableIntStateOf(0)

    fun onUserNameStateChanged(newString: String) {
        userNameState = newString
    }

    fun onUserAgeStateChanged(newInt: Int) {
        userAgeState = newInt

    }

    private lateinit var getAllUsers: Flow<List<User>>

    init {
        viewModelScope.launch {
            getAllUsers = userRepository.getUsers()
        }
    }


    fun addUser(wish: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addAUser(wish = wish)
        }
    }

    fun getUserById(id: Int): Flow<User> {
        return userRepository.getUserById(id = id)
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(user = user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user = user)
        }
    }

}