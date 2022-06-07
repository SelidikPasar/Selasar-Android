package com.selasarteam.selidikpasar.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.databinding.ActivityDetailPriceBinding
import com.selasarteam.selidikpasar.databinding.ActivityMorePriceBinding

class DetailPriceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPriceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val ingredients = resources.getStringArray(R.array.Ingredients)
        val arrayAdapter = ArrayAdapter(applicationContext, R.layout.dropdown_item, ingredients)
        binding.detailPrice.setAdapter(arrayAdapter)
    }
}