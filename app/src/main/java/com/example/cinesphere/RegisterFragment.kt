package com.example.cinesphere

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.cinesphere.data.user.UserModel
import com.example.cinesphere.data.user.UserController
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button

    private val userController by lazy { UserController(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)

        usernameEditText = root.findViewById(R.id.usernameEditText)
        emailEditText = root.findViewById(R.id.emailEditText)
        passwordEditText = root.findViewById(R.id.passwordEditText)
        registerButton = root.findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isBlank() || email.isBlank() || password.isBlank()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newUser = UserModel(username = username, email = email, password = password)

            lifecycleScope.launch {
                try {
                    userController.registerUser(newUser)
                    Toast.makeText(requireContext(), "User registered successfully", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Error registering user", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return root
    }

    private fun registerUser() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        val isRegistrationSuccessful = true

        if (isRegistrationSuccessful) {
            MainActivity.AuthManager.login()

            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()
        } else {
            Toast.makeText(activity, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }
}