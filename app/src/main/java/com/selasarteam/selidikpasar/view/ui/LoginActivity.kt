package com.selasarteam.selidikpasar.view.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.selasarteam.selidikpasar.BuildConfig
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.databinding.ActivityLoginBinding
import com.selasarteam.selidikpasar.model.local.datastore.SessionModel
import com.selasarteam.selidikpasar.view.viewmodel.LoginViewModel
import com.selasarteam.selidikpasar.view.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var factory: ViewModelFactory
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private val viewModel: LoginViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        setupViewModel()
        setupFirebase()
        setupAction()
    }

    private fun setupView() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupFirebase() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.WEB_CLIENT_ID)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    private fun setupAction() {
        binding.apply {
            btnLogin.setOnClickListener { loginValidation() }
            btnRegisterHere.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                finish()
            }
            btnLoginGoogle.setOnClickListener {
                resultLauncher.launch(googleSignInClient.signInIntent)
            }
        }
    }

    private fun loginValidation() {
        binding.apply {
            if (edtEmail.length() == 0 && edtPassword.length() == 0) {
                edtEmail.error = getString(R.string.required_field)
                edtPassword.setError(getString(R.string.required_field), null)
            } else if (edtEmail.length() != 0 && edtPassword.length() != 0) {
                showLoading()
                postLogin()
                showMessage()
                viewModel.login()
                moveActivity()
            }
        }
    }

    private fun showLoading() {
        viewModel.showLoading.observe(this@LoginActivity) {
            binding.pbLogin.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun postLogin() {
        binding.apply {
            viewModel.postLogin(
                edtEmail.text.toString(),
                edtPassword.text.toString()
            )

            viewModel.loginResponse.observe(this@LoginActivity) {
                saveSession(
                    SessionModel(
                        it.loginResult?.name.toString(),
                        it.loginResult?.email.toString(),
                        "",
                        AUTH_KEY + it.loginResult?.token.toString(),
                        true
                    )
                )
            }
        }
    }

    private fun showMessage() {
        viewModel.showMessage.observe(this@LoginActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@LoginActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun moveActivity() {
        viewModel.loginResponse.observe(this@LoginActivity) {
            if (!it.error) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    private var resultLauncher = registerForActivityResult(
        StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                // Google Sign In was successful, authenticate with Firebase
                account.apply {
                    Log.d(TAG, "firebaseAuthWithGoogle: $id")
                    Toast.makeText(
                        this@LoginActivity,
                        "Google sign in success. Welcome $displayName",
                        Toast.LENGTH_SHORT
                    ).show()
                    firebaseAuthWithGoogle(idToken!!)
                    saveSession(
                        SessionModel(
                            displayName.toString(),
                            email.toString(),
                            photoUrl.toString(),
                            idToken.toString(),
                            true
                        )
                    )
                }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed.", e)
                Toast.makeText(
                    this@LoginActivity,
                    "Google sign in failed. $e",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Log.d(TAG, "signInWithCredential: success")
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential: failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun saveSession(session: SessionModel) {
        viewModel.saveSession(session)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    companion object {
        private const val AUTH_KEY = "Bearer "
        private const val TAG = "LoginActivity"
    }
}