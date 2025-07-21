package com.arspectra.poweroffnotification

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val valueTv = findViewById<TextView>(R.id.value)
        val btn = findViewById<Button>(R.id.btn_read)
        btn.setOnClickListener {
            try{
                val value = Settings.Global.getInt(this.contentResolver, "poweroff_notification", 1)
                valueTv.text = "Current value: $value"
            }catch (e: Exception){
            }

        }

        val valueRandomTv = findViewById<TextView>(R.id.value_random)
        val btn2 = findViewById<Button>(R.id.btn_write)
        btn2.setOnClickListener {
            Log.d("MyApp", "Button clicked")
            try{
                val randomValue = Random.nextInt(0, 3)

                val intent = Intent()
                intent.setAction("com.arspectra.ACTION_POWEROFF_NOTIFICATION")
                intent.putExtra("poweroff_value", randomValue)
                this.sendBroadcast(intent)

                valueRandomTv.text = "Set value: $randomValue"
            }catch (e: Exception){

            }

        }
    }
}