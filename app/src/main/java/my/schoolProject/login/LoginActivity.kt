package my.schoolProject.login

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import my.schoolProject.MainActivity
import my.schoolProject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
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
        binding.passwordEditText.setOnEditorActionListener { _, actionID, keyEvent ->
            if (keyEvent != null && keyEvent.keyCode == KeyEvent.KEYCODE_ENTER
                || actionID == EditorInfo.IME_ACTION_DONE
            ) {
                validateAndRedirect()
            }
            false
        }

        viewModel.loginResultLiveData.observe(this){loginResult:Boolean ->
            if (!loginResult){
                binding.errorTv.visibility = View.VISIBLE
            }else{
                val username = binding.emailEditText.text.toString()
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun validateAndRedirect() {
        val username = binding.emailEditText.text.toString()
        if (username.isEmpty()) {
            Toast.makeText(this, "Username empty", Toast.LENGTH_SHORT).show()
            return
        }
        val password = binding.passwordEditText.text.toString()
        if (password.isEmpty()) {
            Toast.makeText(this, "Password Empty", Toast.LENGTH_SHORT).show()
            return
        }
    }
}