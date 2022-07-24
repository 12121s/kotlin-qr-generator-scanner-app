package com.illis.qrgenerator;

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlin.jvm.JvmStatic;

object DataBindingUtil {
    @JvmStatic
    @BindingAdapter("qrCode")
    fun setQRCode(
            view: ImageView,
            qrCode: String?
    ) {
        if (qrCode == null) {
            view.setImageBitmap(null)
            return
        }

        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.encodeBitmap(
            qrCode.toByteArray().toString(Charsets.ISO_8859_1),
            BarcodeFormat.QR_CODE,
            400,
            400
        )

        view.setImageBitmap(bitmap)
    }
}
