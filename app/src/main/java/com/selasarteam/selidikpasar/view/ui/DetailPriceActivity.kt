package com.selasarteam.selidikpasar.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.databinding.ActivityDetailPriceBinding

class DetailPriceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding = ActivityDetailPriceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}