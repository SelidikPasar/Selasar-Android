package com.selasarteam.selidikpasar.view.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.databinding.ActivityDetailPriceBinding
import com.selasarteam.selidikpasar.view.viewmodel.DetailPriceViewModel
import com.selasarteam.selidikpasar.view.viewmodel.ViewModelFactory

class DetailPriceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPriceBinding
    private lateinit var factory: ViewModelFactory
    private val viewModel: DetailPriceViewModel by viewModels { factory }

    companion object {
        const val EXTRA_ITEM_SELECTED = "extra_item_selected"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        binding = ActivityDetailPriceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filters = intent?.getIntExtra(EXTRA_ITEM_SELECTED, R.string.rice) ?: R.string.rice

        binding.detailPrice.setText(filters)

        val listOfArrays = resources.getStringArray(R.array.Ingredients)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listOfArrays)
        binding.detailPrice.setAdapter(adapter)
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }
}