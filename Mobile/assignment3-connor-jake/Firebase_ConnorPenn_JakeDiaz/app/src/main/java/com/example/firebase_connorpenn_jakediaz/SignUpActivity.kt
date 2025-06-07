package com.example.firebase_connorpenn_jakediaz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.BackButton).setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        findViewById<Button>(R.id.SignUpButton).setOnClickListener{
            val db = FirebaseFirestore.getInstance()
            val userEmail = findViewById<EditText>(R.id.EmailTextView).text.toString()
            val password = findViewById<EditText>(R.id.PasswordTextView).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.ConfirmPasswordView).text.toString()

            var exists = false
            db.collection("Users")
                .get()
                .addOnCompleteListener {
                    val result: StringBuffer = StringBuffer()
                    if (it.isSuccessful) {
                        for (document in it.result!!) {
                            if (userEmail == "${document.data.getValue("Email")}") {
                                exists = true
                            }
                        }
                    }

                    if (exists) {
                        val existingUserAlert = AlertDialog.Builder(this)
                        existingUserAlert.setMessage("An account with this email already exists")
                        existingUserAlert.setTitle("Error")
                        existingUserAlert.setCancelable(false)
                        existingUserAlert.setPositiveButton("Ok") { dialog, which ->
                            dialog.cancel()
                        }

                        val failureDialog = existingUserAlert.create()

                        failureDialog.show()
                    } else {
                        if (password.equals(confirmPassword)) {
                            val newUser: MutableMap<String, Any> = HashMap()
                            newUser["Email"] = userEmail
                            newUser["Password"] = password
                            db.collection("Users")
                                .add(newUser)
                                .addOnSuccessListener {
                                    val alert = AlertDialog.Builder(this)
                                    alert.setMessage("You successfully created an account into the awesome app")
                                    alert.setTitle("Success!")
                                    alert.setCancelable(false)
                                    alert.setPositiveButton("Ok") { dialog, which ->
                                        dialog.cancel()
                                    }

                                    val alertDialog = alert.create()

                                    alertDialog.show()
                                }
                        } else {
                            val matchAlert = AlertDialog.Builder(this)
                            matchAlert.setMessage("Passwords do not match")
                            matchAlert.setTitle("Error")
                            matchAlert.setCancelable(false)
                            matchAlert.setPositiveButton("Ok") { dialog, which ->
                                dialog.cancel()
                            }

                            val matchDialog = matchAlert.create()

                            matchDialog.show()
                        }
                    }
                }
        }
    }
}