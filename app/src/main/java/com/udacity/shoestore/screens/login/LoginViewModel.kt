package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.udacity.shoestore.models.User

class LoginViewModel : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _navigateToWelcome = MutableLiveData<Boolean>()
    val navigateToWelcome: LiveData<Boolean>
        get() = _navigateToWelcome

    init {
        _user.value = User(email = "", password = "")
    }

    fun isAuthenticated (): Boolean =  user.value?.email == "harrison@gmail.com" &&  user.value?.password == "123456"

    fun onCreateOrNextClick () {
        _navigateToWelcome.value = isAuthenticated()
    }

    fun onNavigationComplete () {
        _navigateToWelcome.value = null
    }
}
