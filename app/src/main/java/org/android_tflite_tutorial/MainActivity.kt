package org.android_tflite_tutorial

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onRecordClick(v: View?) {

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
