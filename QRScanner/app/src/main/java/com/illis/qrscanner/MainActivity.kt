package com.illis.qrscanner

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : AppCompatActivity() {
    private lateinit var manager : CaptureManager
    private lateinit var barcodeView : DecoratedBarcodeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barcodeView = findViewById(R.id.qr_scanner)

        manager = CaptureManager(this, barcodeView)
        manager.initializeFromIntent(intent, savedInstanceState)
        manager.decode()

//        runQRcodeReader()
    }

    override fun onResume() {
        super.onResume()
        manager.onResume()
    }

    override fun onPause() {
        super.onPause()
        manager.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        manager.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        manager.onSaveInstanceState(outState)
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