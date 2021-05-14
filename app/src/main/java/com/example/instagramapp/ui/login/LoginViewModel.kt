package com.example.instagramapp.ui.login

import androidx.lifecycle.ViewModel
import com.example.instagramapp.data.repository.UserRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

}
