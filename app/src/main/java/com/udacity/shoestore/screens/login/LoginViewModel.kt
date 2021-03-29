package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.udacity.shoestore.models.User

class LoginViewModel : ViewModel() {

    val user = MutableLiveData<User>()

    private val _navigateToWelcome = MutableLiveData<Boolean>()
    val navigateToWelcome: LiveData<Boolean>
        get() = _navigateToWelcome

    init {
        user.value = User(email = "", password = "")
    }

    fun isAuthenticated (): Boolean {
        if (user.value?.email == "harrison@gmail.com" &&  user.value?.password == "123456") {
            return true
        }
        return false
    }

    fun onCreateOrNextClick () {
        _navigateToWelcome.value = isAuthenticated()
    }
}
