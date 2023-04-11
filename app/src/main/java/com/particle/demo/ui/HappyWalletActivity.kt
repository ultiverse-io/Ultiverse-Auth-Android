package com.particle.demo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.minijoy.demo.R
import com.minijoy.demo.databinding.ActivityCallbackBinding
import com.minijoy.demo.databinding.ActivityMainBinding
import com.particle.api.infrastructure.db.table.Wallet
import com.particle.base.ParticleNetwork
import com.particle.connect.ParticleConnect
import com.particle.connect.ParticleConnectAdapter
import com.particle.gui.ParticleWallet
import com.particle.gui.router.PNRouter
import com.particle.gui.router.RouterPath
import com.particle.network.ParticleNetworkAuth
import com.particle.network.ParticleNetworkAuth.getAddress
import com.particle.network.ParticleNetworkAuth.openWebWallet
import com.particle.network.ParticleNetworkAuth.setUserInfo
import com.particle.network.ParticleNetworkAuth.signAndSendTransaction
import com.particle.network.ParticleNetworkAuth.signMessage
import com.particle.network.service.WebServiceCallback
import com.particle.network.service.model.WebOutput
import com.particle.network.service.model.WebServiceError
import kotlinx.coroutines.launch

class HappyWalletActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCallbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_callback)

        processData(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        processData(intent)
    }

    fun processData(intent: Intent) {
        val userInfoJson = intent.data?.getQueryParameter("userInfo")
        LogUtils.d("userInfoJson", userInfoJson)
        userInfoJson?.let { it ->
            val isSuccess = ParticleNetwork.setUserInfo(it)
            if (isSuccess) {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
                binding.tvAddress.text ="钱包地址："+ ParticleNetwork.getAddress()
            } else {
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show()
                finish()
            }
            //ParticleNetwork.signAndSendTransaction()
            // ...
        }
    }
}