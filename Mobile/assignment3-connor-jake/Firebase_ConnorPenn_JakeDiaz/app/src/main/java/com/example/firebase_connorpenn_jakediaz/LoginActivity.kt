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

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.BackButton).setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        findViewById<Button>(R.id.LoginButton).setOnClickListener{
            val db = FirebaseFirestore.getInstance()
            val userEmail = findViewById<EditText>(R.id.EmailTextLogin).text.toString()
            val password = findViewById<EditText>(R.id.PasswordTextLogin).text.toString()
            var found = false

            db.collection("Users")
                .get()
                .addOnCompleteListener{
                    val result: StringBuffer = StringBuffer()
                    if (it.isSuccessful) {
                        for (document in it.result!!) {
                            if (userEmail == "${document.data.getValue("Email")}" && password == "${document.data.getValue("Password")}") {
                                found = true
                            }
                            else {
                                found = false
                            }
                        }
                        if (found) {
                            startActivity(Intent(this, HomePage::class.java))
                        }
                        else {
                            val alert = AlertDialog.Builder(this)
                            alert.setMessage("Either your id or password is incorrect")
                            alert.setTitle("Failed.")
                            alert.setCancelable(false)
                            alert.setPositiveButton("Ok") {
                                    dialog, which -> dialog.cancel()
                            }

                            val alertDialog = alert.create()

                            alertDialog.show()

                        }
                    }
                }
        }
    }
}