package com.selasarteam.selidikpasar.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.selasarteam.selidikpasar.R
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
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.rice)
                it.putExtra(DetailPriceActivity.EXTRA_SUB_ITEM_SELECTED, R.array.rice_array)
            })
        }

        binding.tbChicken.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.chicken)
                it.putExtra(DetailPriceActivity.EXTRA_SUB_ITEM_SELECTED, R.array.chicken_array)
            })
        }

        binding.tbBeef.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.beef)
                it.putExtra(DetailPriceActivity.EXTRA_SUB_ITEM_SELECTED, R.array.beef_array)
            })
        }

        binding.tbEgg.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.egg)
                it.putExtra(DetailPriceActivity.EXTRA_SUB_ITEM_SELECTED, R.array.egg_array)
            })
        }

        binding.tbShallot.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.shallot)
                it.putExtra(DetailPriceActivity.EXTRA_SUB_ITEM_SELECTED, R.array.shallot_array)
            })
        }

        binding.tbGarlic.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.garlic)
                it.putExtra(DetailPriceActivity.EXTRA_SUB_ITEM_SELECTED, R.array.garlic_array)
            })
        }

        binding.tbRedChilli.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.red_chilli)
                it.putExtra(DetailPriceActivity.EXTRA_SUB_ITEM_SELECTED, R.array.red_chilli_array)
            })
        }

        binding.tbCayyene.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.cayenne_pepper)
                it.putExtra(DetailPriceActivity.EXTRA_SUB_ITEM_SELECTED, R.array.cayenne_pepper_array)
            })
        }

        binding.tbOil.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.cooking_oil)
                it.putExtra(DetailPriceActivity.EXTRA_SUB_ITEM_SELECTED, R.array.cooking_oil_array)
            })
        }

        binding.tbSugar.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.sugar)
                it.putExtra(DetailPriceActivity.EXTRA_SUB_ITEM_SELECTED, R.array.sugar_array)
            })
        }
    }
}