package com.illis.qrgenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.illis.qrgenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this, SavedStateViewModelFactory(application, this))[MainViewModel::class.java]

        supportFragmentManager.beginTransaction().add(R.id.page_bird, BirdFragment()).commit()
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_bird -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.page_bird, BirdFragment())
                        .commitAllowingStateLoss()
                    viewModel.qrCode = "bird"
                    return@setOnItemSelectedListener true
                }
                R.id.page_cat -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.page_cat, CatFragment())
                        .commitAllowingStateLoss()
                    viewModel.qrCode = "cat"
                    return@setOnItemSelectedListener true
                }
                R.id.page_dog -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.page_dog, DogFragment())
                        .commitAllowingStateLoss()
                    viewModel.qrCode = "dog"
                    return@setOnItemSelectedListener true
                }
            }

            return@setOnItemSelectedListener false
        }

    }
}