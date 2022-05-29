package com.selasarteam.selidikpasar.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.selasarteam.selidikpasar.databinding.ActivityLoginBinding
import com.selasarteam.selidikpasar.databinding.ActivityMainBinding
import com.selasarteam.selidikpasar.databinding.ActivityRegisterBinding
import com.selasarteam.selidikpasar.view.model.LoginViewModel
import com.selasarteam.selidikpasar.view.model.RegisterViewModel
import com.selasarteam.selidikpasar.view.model.ViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var factory: ViewModelFactory
    private val viewModel: RegisterViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        setupViewModel()
    }

    private fun setupView() {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }
}