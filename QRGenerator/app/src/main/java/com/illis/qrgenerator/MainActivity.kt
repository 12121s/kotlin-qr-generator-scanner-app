package com.illis.qrgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.navigation.NavigationBarView
import com.illis.qrgenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        supportFragmentManager.beginTransaction().add(R.id.page_bird, BirdFragment()).commit()
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_bird -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.page_bird , BirdFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.page_cat -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.page_cat, CatFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.page_dog -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.page_dog, DogFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }

            return@setOnItemSelectedListener false
        }

    }
}