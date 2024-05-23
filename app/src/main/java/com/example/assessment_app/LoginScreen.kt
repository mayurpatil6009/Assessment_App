package com.example.assessment_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.assessment_app.databinding.ActivityLoginScreenBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginScreen : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    private val binding: ActivityLoginScreenBinding by lazy {
        ActivityLoginScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.LoginBtn.setOnClickListener {
            val name = binding.edtNameUser.text.toString()
            val password = binding.edtPasswordUser.text.toString()
            val uniqueID = binding.edtUniqueIDUser.text.toString()
            val user = UserData(name, password, uniqueID)
            databaseReference = FirebaseDatabase.getInstance().getReference("Users")
            databaseReference.child(uniqueID).setValue(user).addOnSuccessListener {
                binding.edtNameUser.text.clear()
                binding.edtPasswordUser.text.clear()
                binding.edtUniqueIDUser.text.clear()
                Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            }.addOnSuccessListener {
                Toast.makeText(this, "Failed to login", Toast.LENGTH_SHORT).show()
            }
        }
    }
}