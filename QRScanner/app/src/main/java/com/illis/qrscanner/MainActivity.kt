package com.illis.qrscanner

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.*

class MainActivity : AppCompatActivity() {
    private lateinit var barcodeView : DecoratedBarcodeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runQRcodeReader()
        barcodeView = findViewById(R.id.qr_scanner)
        barcodeView.run {
            this.decoderFactory = DefaultDecoderFactory(mutableListOf(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39))
            initializeFromIntent(intent)
            decodeContinuous {
                Log.d("TEST", "message=${it.text}")
                barcodeView.stopDecoding()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }

    private fun runQRcodeReader() {
        val scanOptions = ScanOptions().setOrientationLocked(true)
        scanOptions.setBarcodeImageEnabled(false)
        scanOptions.setBeepEnabled(false)
        scanOptions.captureActivity = MainActivity::class.java
        barcodeLauncher.launch(scanOptions)
    }

    private val barcodeLauncher = registerForActivityResult(ScanContract()) {
            result ->
        if(result.contents == null) {
            val originalIntent = result.originalIntent
            if (originalIntent == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else if(originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                Toast.makeText(this, "Cancelled due to missing camera permission", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Scanned: ${result.contents}", Toast.LENGTH_LONG).show()
        }
    }
}