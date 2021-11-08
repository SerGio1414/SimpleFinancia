package com.example.finanzasapp147324

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OpenFullScreenListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setupWithNavController(Navigation.findNavController(this, R.id.nav_host_fragment))
    }
