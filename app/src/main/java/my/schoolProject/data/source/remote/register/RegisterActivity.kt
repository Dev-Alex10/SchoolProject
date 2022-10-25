package my.schoolProject.data.source.remote.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import my.schoolProject.SchoolProjectApplication
import my.schoolProject.databinding.ActivityRegisterBinding
import my.schoolProject.data.source.remote.login.LoginActivity

private const val TAG = "RegisterActivity"

class RegisterActivity : AppCompatActivity() {
    private val viewModel by viewModels<RegisterViewModel> {
        RegisterViewModelFactory((application as SchoolProjectApplication))
    }
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
        viewModel.loadUsers().value
    }

    private fun setup() {
        binding.btnSignUp.setOnClickListener {
            viewModel.loadUsers().observe(this) {
                Log.d(TAG, "$it \n")
            }
            validateAndInsert()
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
        }
    }

    private fun validateAndInsert() {
        val name = binding.nameEditText.text.toString()
        if (name.isEmpty()) {
            Toast.makeText(this, "Name empty", Toast.LENGTH_SHORT).show()
            return
        }
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
        viewModel.registerUser(
            binding.nameEditText.text.toString(),
            binding.emailEditText.text.toString(),
            binding.passwordEditText.text.toString()
        )
    }
}
