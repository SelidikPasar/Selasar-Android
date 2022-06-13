package com.selasarteam.selidikpasar.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.databinding.FragmentPriceBinding
import com.selasarteam.selidikpasar.view.viewmodel.ViewModelFactory

class PriceFragment : Fragment() {

    private var _binding: FragmentPriceBinding? = null
    private val binding get() = _binding!!
    private lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupMore()
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(requireContext())
    }

    private fun setupMore() {
        binding.ibRice.setOnClickListener {
            activity?.startActivity(Intent(activity, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.rice)
            })
        }

        binding.ibChicken.setOnClickListener {
            activity?.startActivity(Intent(activity, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.chicken)
            })
        }

        binding.ibBeef.setOnClickListener {
            activity?.startActivity(Intent(activity, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.beef)
            })
        }

        binding.ibEgg.setOnClickListener {
            activity?.startActivity(Intent(activity, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.egg)
            })
        }

        binding.ibShallot.setOnClickListener {
            activity?.startActivity(Intent(activity, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.shallot)
            })
        }

        binding.ibGarlic.setOnClickListener {
            activity?.startActivity(Intent(activity, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.garlic)
            })
        }

        binding.ibRedChilli.setOnClickListener {
            activity?.startActivity(Intent(activity, DetailPriceActivity::class.java).also {
                it.putExtra(DetailPriceActivity.EXTRA_ITEM_SELECTED, R.string.red_chilli)
            })
        }

        binding.ibMore.setOnClickListener {
            val intent = Intent(activity, MorePriceActivity::class.java)
            activity?.startActivity(intent)

//            childFragmentManager.beginTransaction().apply {
//                replace(R.id.price_fragment, itemFragment)
//                commit()
//            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}