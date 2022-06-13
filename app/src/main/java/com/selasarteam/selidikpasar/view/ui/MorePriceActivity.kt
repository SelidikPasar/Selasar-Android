package com.selasarteam.selidikpasar.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.selasarteam.selidikpasar.databinding.ActivityMorePriceBinding

class MorePriceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMorePriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        ingredientsDetail()
    }

    private fun setupView() {
        binding = ActivityMorePriceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun ingredientsDetail() {
        binding.tbRice.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.tbChicken.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.tbBeef.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.tbEgg.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.tbShallot.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.tbGarlic.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.tbRedChilli.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.tbCayyene.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.tbOil.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }

        binding.tbSugar.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }
    }
}