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
            })
        }

        binding.tbChicken.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.chicken)
            })
        }

        binding.tbBeef.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.beef)
            })
        }

        binding.tbEgg.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.egg)
            })
        }

        binding.tbShallot.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.shallot)
            })
        }

        binding.tbGarlic.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.garlic)
            })
        }

        binding.tbRedChilli.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.red_chilli)
            })
        }

        binding.tbCayyene.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.cayenne_pepper)
            })
        }

        binding.tbOil.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.cooking_oil)
            })
        }

        binding.tbSugar.setOnClickListener {
            this.startActivity(Intent(this, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.sugar)
            })
        }
    }
}