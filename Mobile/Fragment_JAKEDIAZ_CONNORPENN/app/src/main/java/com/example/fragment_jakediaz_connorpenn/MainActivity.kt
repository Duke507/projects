package com.example.fragment_jakediaz_connorpenn

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val barFragment = BarFragment()
        val lineFragment = LineFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fContainer,lineFragment)
            commit()
        }
        findViewById<Button>(R.id.bBar).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fContainer,barFragment)
                commit()
            }
        }
        findViewById<Button>(R.id.bLine).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fContainer,lineFragment)
                commit()
            }
        }
    }
}