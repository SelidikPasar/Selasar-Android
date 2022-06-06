package com.selasarteam.selidikpasar.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.databinding.ActivityMorePriceBinding

class MorePriceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMorePriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMorePriceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        rice()
        chicken()
        beef()
        egg()
        shallot()
        garlic()
        redChilli()
        cayenne()
        oil()
        sugar()
    }


    private fun rice(){
        binding.btnRice.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }
    }


    private fun chicken(){
        binding.btnChicken.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }
    }


    private fun beef(){
        binding.btnBeef.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }
    }


    private fun egg(){
        binding.btnEgg.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }
    }


    private fun shallot(){
        binding.btnShallot.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }
    }


    private fun garlic(){
        binding.btnGarlic.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }
    }


    private fun redChilli(){
        binding.btnRedchilli.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }
    }


    private fun cayenne(){
        binding.btnCayenne.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }
    }


    private fun oil(){
        binding.btnOil.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }
    }


    private fun sugar(){
        binding.btnSugar.setOnClickListener {
            val intent = Intent(this, DetailPriceActivity::class.java)
            startActivity(intent)
        }
    }
}