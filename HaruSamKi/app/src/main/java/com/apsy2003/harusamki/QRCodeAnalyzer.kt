package com.apsy2003.harusamki

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class QRCodeAnalyzer(val onDetectListener: OnDetectListener)  : ImageAnalysis.Analyzer{

    private val scanner = BarcodeScanning.getClient()

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            // 1) 이미지가 찍힐 당시 카메라의 회전 각도를 고려하여 입력 이미지를 생성
            val image =
                InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            // 2) scanner.process(image)를 통해 이미지를 분석
            scanner.process(image)
                .addOnSuccessListener { qrCodes ->
                    // 리스너가 들어갈 자리
                    for (qrCode in qrCodes) {
                        onDetectListener.onDetect(qrCode.rawValue ?: "")
                    }
                }
                .addOnFailureListener {
                    it.printStackTrace()
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }
}