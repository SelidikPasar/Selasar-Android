package com.selasarteam.selidikpasar.view.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.databinding.FragmentNewsBinding
import com.selasarteam.selidikpasar.utils.Result
import com.selasarteam.selidikpasar.view.adapter.ListNewsAdapter
import com.selasarteam.selidikpasar.view.model.NewsViewModel
import com.selasarteam.selidikpasar.view.model.ViewModelFactory

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private var factory: ViewModelFactory? = null
    private val viewModel: NewsViewModel by viewModels { factory!! }

    private var newsAdapter: ListNewsAdapter? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupAdapter()
        setupList()
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(requireContext())
    }

    private fun setupAdapter() {
        newsAdapter = ListNewsAdapter()

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    private fun setupList() {
        viewModel.getSummaryNews().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.pbNews.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.pbNews.visibility = View.GONE
                        val newsData = result.data
                        newsAdapter?.submitList(newsData)
                    }
                    is Result.Error -> {
                        binding.pbNews.visibility = View.GONE
                        Toast.makeText(
                            context,
                            getString(R.string.error_response) + result.error,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}