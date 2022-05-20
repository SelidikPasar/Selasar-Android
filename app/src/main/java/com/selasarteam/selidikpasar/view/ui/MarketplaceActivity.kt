package com.selasarteam.selidikpasar.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.selasarteam.selidikpasar.databinding.ActivityMarketplaceBinding

class MarketplaceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMarketplaceBinding
//    private lateinit var factory: ViewModelFactory
//    private val profileViewModel: MarketplaceViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
    }

    private fun setupView() {
        binding = ActivityMarketplaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}