package com.selasarteam.selidikpasar.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.databinding.ActivityRegisterBinding
import com.selasarteam.selidikpasar.view.viewmodel.RegisterViewModel
import com.selasarteam.selidikpasar.view.viewmodel.ViewModelFactory

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
            btnRegister.setOnClickListener { registerValidation() }
            btnLoginHere.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun registerValidation() {
        binding.apply {
            if (edtName.length() == 0 && edtEmail.length() == 0 && edtPassword.length() == 0) {
                edtName.error = getString(R.string.required_field)
                edtEmail.error = getString(R.string.required_field)
                edtPassword.setError(getString(R.string.required_field), null)
            } else if (!cbValidation.isChecked) {
                Toast.makeText(
                    this@RegisterActivity,
                    getString(R.string.required_cb),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (edtName.length() != 0 && edtEmail.length() != 0 && edtPassword.length() != 0 && cbValidation.isChecked) {
                showLoading()
                postRegister()
                showMessage()
                moveActivity()
            }
        }
    }

    private fun showLoading() {
        viewModel.showLoading.observe(this@RegisterActivity) {
            binding.pbRegister.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun postRegister() {
        binding.apply {
            viewModel.postRegister(
                edtName.text.toString(),
                edtEmail.text.toString(),
                edtPassword.text.toString()
            )
        }
    }

    private fun showMessage() {
        viewModel.showMessage.observe(this@RegisterActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@RegisterActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun moveActivity() {
        viewModel.registerResponse.observe(this@RegisterActivity) {
            if (!it.error) {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
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