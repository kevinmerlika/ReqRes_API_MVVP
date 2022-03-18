package com.example.peopleproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.peopleproject.repository.ProfileRepository

class ProfileViewModelFactory constructor(private val repository: ProfileRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            ProfileViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}