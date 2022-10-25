package my.schoolProject.data.source.remote.login

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import my.schoolProject.MainActivity
import my.schoolProject.SchoolProjectApplication
import my.schoolProject.databinding.ActivityLoginBinding
import my.schoolProject.data.source.remote.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory((application as SchoolProjectApplication).repository)
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        binding.btnSignIn.setOnClickListener {
            validateAndRedirect()
        }
        binding.btnSignUp.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
            finish()
        }
        binding.passwordEditText.setOnEditorActionListener { _, actionID, keyEvent ->
            if (keyEvent != null && keyEvent.keyCode == KeyEvent.KEYCODE_ENTER
                || actionID == EditorInfo.IME_ACTION_DONE
            ) {
                validateAndRedirect()
            }
            false
        }

        viewModel.loginResultLiveData.observe(this) { loginResult: Boolean ->
            if (!loginResult) {
                binding.errorTv.visibility = View.VISIBLE
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun validateAndRedirect() {
        val email = binding.emailEditText.text.toString()
        if (email.isEmpty()) {
            Toast.makeText(this, "Email empty", Toast.LENGTH_SHORT).show()
            return
        }
        val password = binding.passwordEditText.text.toString()
        if (password.isEmpty()) {
            Toast.makeText(this, "Password Empty", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.areCredentialsValid(email, password)
    }
}
