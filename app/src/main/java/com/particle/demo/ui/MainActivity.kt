package com.particle.demo.ui

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.minijoy.demo.R
import com.minijoy.demo.databinding.ActivityMainBinding
import com.particle.base.ParticleNetwork
import com.particle.browser.utils.auth.BrowserUtils


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.btLogin.setOnClickListener {
            val projectId = ParticleNetwork.getProjectUUID()
            val clientKey = ParticleNetwork.getProjectClientID()
            val appId = ParticleNetwork.getProjectAppUUID()
            BrowserUtils.loadURI(Uri.parse("https://web-demo.particle.network/webRedirect?&projectId=$projectId&clientKey=$clientKey&appId=$appId"))
        }
    }
}