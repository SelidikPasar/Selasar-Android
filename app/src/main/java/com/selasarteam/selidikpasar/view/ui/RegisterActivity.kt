package com.selasarteam.selidikpasar.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.selasarteam.selidikpasar.databinding.ActivityRegisterBinding
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
        setupAction()
    }

    private fun setupAction() {
        binding.apply {
            btnLoginHere.setOnClickListener {
                startActivity(
                    Intent(
                        this@RegisterActivity,
                        LoginActivity::class.java
                    )
                )
                finish()
            }
        }
    }

    private fun setupView() {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }
}