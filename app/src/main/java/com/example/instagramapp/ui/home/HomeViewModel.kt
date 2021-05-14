package com.example.instagramapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramapp.data.remote.response.UserResponse
import com.example.instagramapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _userState = MutableStateFlow(UserResponse("", "", "", ""))
    val userState: StateFlow<UserResponse> = _userState

    init {
        viewModelScope.launch {
            _userState.value = userRepository.getUser()
        }
    }
}
