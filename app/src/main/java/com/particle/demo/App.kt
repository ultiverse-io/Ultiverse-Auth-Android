package com.particle.demo

import android.app.Application
import com.particle.base.Env
import com.particle.base.EthereumChain
import com.particle.base.EthereumChainId
import com.particle.base.ParticleNetwork


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ParticleNetwork.init(this, Env.DEV, EthereumChain(EthereumChainId.Mainnet))
    }

}
