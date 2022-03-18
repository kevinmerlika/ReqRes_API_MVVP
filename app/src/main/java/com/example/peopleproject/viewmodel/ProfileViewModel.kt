package com.example.peopleproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.peopleproject.data.Data
import com.example.peopleproject.repository.ProfileRepository
import kotlinx.coroutines.*

class ProfileViewModel constructor(private val repository: ProfileRepository):ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val userList = MutableLiveData<List<Data>>()
    val loading = MutableLiveData<Boolean>()

    var job:Job? = null

    fun getAllUsers(){

       job= CoroutineScope(Dispatchers.IO).launch {
            val response =repository.getList()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    userList.postValue(response.body()?.data)
                    loading.value = false
                }else{
                    OnError("Error :  ${response.message()}")

                }
            }
        }

    }

    private fun OnError(message: String) {
        errorMessage.value = message
        loading.value = false

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}