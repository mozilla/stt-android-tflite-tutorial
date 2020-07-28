package org.android_tflite_tutorial

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.mozilla.deepspeech.libdeepspeech.DeepSpeechModel
import java.io.File


class MainActivity : AppCompatActivity() {
    private var model: DeepSpeechModel? = null

    private val TFLITE_MODEL_FILENAME = "deepspeech-0.7.4-models.tflite"
    private val SCORER_FILENAME = "deepspeech-0.7.4-models.scorer"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkRecordAudioPermission()

        // Create application data directory on the device
        val modelsPath = getExternalFilesDir(null).toString()

        status.text = "Ready. Copy model files to \"$modelsPath\" if running for the first time.\n"
    }

    private fun checkRecordAudioPermission() {
        // Permission is automatically granted on SDK < 23 upon installation.
        if (Build.VERSION.SDK_INT >= 23) {
            val permission = Manifest.permission.RECORD_AUDIO

            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(permission), 3)
            }
        }
    }

    private fun createModel(): Boolean {
        val modelsPath = getExternalFilesDir(null).toString()
        val tfliteModelPath = "$modelsPath/$TFLITE_MODEL_FILENAME"
        val scorerPath = "$modelsPath/$SCORER_FILENAME"

        for (path in listOf(tfliteModelPath, scorerPath)) {
            if (!File(path).exists()) {
                status.append("Model creation failed: $path does not exist.\n")
                return false
            }
        }

        model = DeepSpeechModel(tfliteModelPath)
        model?.enableExternalScorer(scorerPath)

        return true
    }

    fun onRecordClick(v: View?) {
        if (model == null) {
            if (!createModel()) {
                return
            }
            status.append("Created model.\n")
        } else {
            status.append("Model already created.\n")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (model != null) {
            model?.freeModel()
        }
    }
}
