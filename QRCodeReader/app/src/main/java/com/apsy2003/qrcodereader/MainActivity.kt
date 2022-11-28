package com.apsy2003.qrcodereader

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.apsy2003.qrcodereader.databinding.ActivityMainBinding
import com.google.common.util.concurrent.ListenableFuture

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>

    private val PERMISSIONS_REQUEST_CODE = 1 // 1)
    private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.CAMERA) // 2)카메라 권한 지정

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (!hasPermissions(this)) {
            // 카메라 권한을 요청합니다.
            requestPermissions(PERMISSIONS_REQUIRED, PERMISSIONS_REQUEST_CODE)
        } else {
            // 만약 이미 권한이 있다면 카메라를 시작합니다.
            startCamera()
        }
    }

    fun hasPermissions(context: Context) = PERMISSIONS_REQUIRED.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    //미리보기와 이미지 분석을 시작
    fun startCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            val preview = getPreview()
            //후면카메라 선택
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            //미리보기 기능 선택
            cameraProvider.bindToLifecycle(this, cameraSelector,preview)

        }, ContextCompat.getMainExecutor(this))

    }
    //미리보기 객체를 반환
    fun getPreview(): Preview{ //Preview 객체 생성
        val preview : Preview = Preview.Builder().build()
        preview.setSurfaceProvider(binding.barcodePreview.getSurfaceProvider())

        return preview
    }
}