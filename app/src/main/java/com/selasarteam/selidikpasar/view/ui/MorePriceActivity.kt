package com.selasarteam.selidikpasar.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.selasarteam.selidikpasar.databinding.ActivityMorePriceBinding

class MorePriceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMorePriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMorePriceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ingredientsDetail()
    }

    private fun ingredientsDetail() {
        binding.btnRice.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.btnChicken.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.btnBeef.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.btnEgg.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.btnShallot.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.btnGarlic.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.btnRedchilli.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.btnCayenne.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.btnOil.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.btnSugar.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }
    }
}