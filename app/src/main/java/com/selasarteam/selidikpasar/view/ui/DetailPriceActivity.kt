package com.selasarteam.selidikpasar.view.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
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
    private lateinit var priceAdapter: ListPriceAdapter
    private val viewModel: DetailPriceViewModel by viewModels { factory }

    companion object {
        const val EXTRA_ITEM_SELECTED = "extra_item_selected"
        const val EXTRA_SUB_ITEM_SELECTED = "extra_sub_item_selected"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupViewModel()
        setupAdapter()
        setupAction()
    }

    private fun setupView() {
        binding = ActivityDetailPriceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filters = intent?.getIntExtra(EXTRA_ITEM_SELECTED, R.string.rice) ?: R.string.rice
        binding.detailPrice.setText(filters)

        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.Ingredients)
        )
        binding.detailPrice.setAdapter(arrayAdapter)
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupAdapter() {
        priceAdapter = ListPriceAdapter()
        with(binding.rvPrice) {
            layoutManager = LinearLayoutManager(this@DetailPriceActivity)
            setHasFixedSize(true)
            adapter = priceAdapter
        }
    }

    private fun setupAction() {
        var subFilters =
            intent?.getIntExtra(EXTRA_SUB_ITEM_SELECTED, R.array.rice_array) ?: R.array.rice_array

        initializeSubFilters(subFilters)

        binding.detailPrice.setOnItemClickListener { _, _, position, _ ->
            val newSubfilters = when (position) {
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
            subFilters = newSubfilters
            initializeSubFilters(newSubfilters)
        }

        binding.subDetailPrice.setOnItemClickListener { _, _, position, _ ->
            val localizedContext =
                this.createConfigurationContext(Configuration(this.resources.configuration).also {
                    it.setLocale(Locale("in"))
                })
            priceAdapter.setFilter(localizedContext.resources.getStringArray(subFilters)[position])
            viewModel.getPriceList()
            viewModel.listPrice.observe(this) {
                showLoading()
                if (it == null) {
                    showMessage()
                    binding.ivNoData.visibility = View.VISIBLE
                    binding.rvPrice.visibility = View.GONE
                } else {
                    priceAdapter.setList(it.regionalPrices)
                }
            }
        }
    }

    private fun initializeSubFilters(resId: Int) {
        val newAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(resId)
        )
        binding.subDetailPrice.setAdapter(newAdapter)
        binding.subDetailPrice.setText("")
    }

    private fun showLoading() {
        viewModel.showLoading.observe(this@DetailPriceActivity) {
            binding.pbPrice.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun showMessage() {
        viewModel.showMessage.observe(this@DetailPriceActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@DetailPriceActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}