package com.example.logingrupalapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    // debo hace la variable privada por encapsulamiento
    // <> es solo para probar con un string

    private val mutableLiveData = MutableLiveData<String>()

    // La siguiente linea es para que no te cambien el estado desde otra clas o desde otro viewmodel


    fun state(): LiveData<String> = mutableLiveData

    //parte corrutina
    fun initViewModel(myString: String) {
        viewModelScope.launch {
            mutableLiveData.postValue(myString)
        }
    }
}