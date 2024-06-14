package com.example.cinesphere

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cinesphere.data.user.UserModel

class ProfileFragment : Fragment() {

    private lateinit var usernameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        usernameTextView = view.findViewById(R.id.username)
        emailTextView = view.findViewById(R.id.email)
        logoutButton = view.findViewById(R.id.logoutButton)

        val user = getUserFromSharedPreferences(requireContext())
        if (user != null) {
            usernameTextView.text = user.username
            emailTextView.text = user.email
        }

        logoutButton.setOnClickListener {
            logout()
        }

        return view
    }

    private fun logout() {
        clearUserFromSharedPreferences(requireContext())

        val intent = Intent(requireContext(), AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }

    private fun getUserFromSharedPreferences(context: Context): UserModel? {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", null)
        val username = sharedPreferences.getString("username", null)
        val email = sharedPreferences.getString("email", null)

        return if (userId != null && username != null && email != null) {
            UserModel(id, username, email, "")
        } else {
            null
        }
    }

    private fun clearUserFromSharedPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
