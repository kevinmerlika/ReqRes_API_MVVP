package com.example.peopleproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.peopleproject.databinding.ActivityMainBinding
import com.example.peopleproject.network.RetrofitService
import com.example.peopleproject.repository.ProfileRepository
import com.example.peopleproject.viewmodel.ProfileViewModel
import com.example.peopleproject.viewmodel.ProfileViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var  viewmodel:ProfileViewModel
    lateinit var binding: ActivityMainBinding
    private val adapter= ProfileAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = RetrofitService.getInstance()
        val repository = ProfileRepository(retrofitService)

        binding.recyclerView.adapter = adapter

        viewmodel = ViewModelProvider(this,ProfileViewModelFactory(repository)).get(ProfileViewModel::class.java)

        viewmodel.userList.observe(this,{
            adapter.setProfiles(it)
        })

        viewmodel.errorMessage.observe(this,{
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewmodel.loading.observe(this,{
            if(it){
                binding.progressDialogue.visibility = View.VISIBLE
            }else{
                binding.progressDialogue.visibility = View.GONE

            }
        })
      viewmodel.getAllUsers()

    }
}