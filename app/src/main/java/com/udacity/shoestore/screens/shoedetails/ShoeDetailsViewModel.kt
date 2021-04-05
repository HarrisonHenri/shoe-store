package com.udacity.shoestore.screens.shoedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailsViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val company = MutableLiveData<String>()
    val size = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    private val _navigateToShoesList = MutableLiveData<Boolean>()
    val navigateToShoesList: LiveData<Boolean>
        get() = _navigateToShoesList
    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    init {
        name.value = ""
        company.value = ""
        size.value = ""
        description.value = ""
        _shoe.value = null
    }

    private fun isShoeValid (): Boolean =  name.value != "" &&  size.value != "" &&  company.value != "" &&  description.value != ""

    fun onSaveClick () {
        val isShoeValid = isShoeValid()
        if (isShoeValid()) {
            _shoe.value = Shoe(name = name.value ?: "", size = size.value?.toDouble() ?: 0.0, description = description.value ?: "", company = company.value ?: "")
        }
        _navigateToShoesList.value = true
    }

    fun onCancelClick () {
        _navigateToShoesList.value = true
    }

    fun onNavigationComplete () {
        _navigateToShoesList.value = null
    }
}
