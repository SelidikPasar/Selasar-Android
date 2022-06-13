package com.selasarteam.selidikpasar.view.ui

import android.content.res.Configuration
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.databinding.ActivityDetailPriceBinding
import com.selasarteam.selidikpasar.view.adapter.ListPriceAdapter
import com.selasarteam.selidikpasar.view.viewmodel.DetailPriceViewModel
import com.selasarteam.selidikpasar.view.viewmodel.ViewModelFactory
import java.util.*

class DetailPriceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPriceBinding
    private lateinit var factory: ViewModelFactory
    private val viewModel: DetailPriceViewModel by viewModels { factory }

    companion object {
        const val EXTRA_ITEM_SELECTED = "extra_item_selected"
        const val EXTRA_SUB_ITEM_SELECTED = "extra_sub_item_selected"
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
        var subfilters = intent?.getIntExtra(EXTRA_SUB_ITEM_SELECTED, R.array.rice_array) ?: R.array.rice_array

        binding.detailPrice.setText(filters)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, resources.getStringArray(R.array.Ingredients))
        binding.detailPrice.setAdapter(arrayAdapter)

        initializeSubFilters(subfilters)

        val priceAdapter = ListPriceAdapter()
        with(binding.rvPrice)  {
            layoutManager = LinearLayoutManager(this@DetailPriceActivity)
            setHasFixedSize(true)
            adapter = priceAdapter
        }

        binding.detailPrice.setOnItemClickListener { _, _, position, _ ->
            val newSubfilters = when(position) {
                0 -> R.array.rice_array
                1 -> R.array.chicken_array
                2 -> R.array.beef_array
                3 -> R.array.egg_array
                4 -> R.array.shallot_array
                5 -> R.array.garlic_array
                6 -> R.array.red_chilli_array
                7 -> R.array.cayenne_pepper_array
                8 -> R.array.cooking_oil_array
                9 -> R.array.sugar_array
                else -> R.array.rice_array
            }
            subfilters = newSubfilters
            initializeSubFilters(newSubfilters)
        }

        binding.subDetailPrice.setOnItemClickListener { _, _, position, _ ->
            val localizedContext = this.createConfigurationContext(Configuration(this.resources.configuration).also {
                it.setLocale(Locale("in"))
            })
            viewModel.getPriceList()
            viewModel.listPrice.observe(this) {
                priceAdapter.setFilter(localizedContext.resources.getStringArray(subfilters)[position])
                priceAdapter.setList(it.regionalPrices)
            }
        }
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun initializeSubFilters(resId: Int) {
        val newAdapter = ArrayAdapter(this,
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(resId)
        )
        binding.subDetailPrice.setAdapter(newAdapter)
        binding.subDetailPrice.setText("")
    }
}