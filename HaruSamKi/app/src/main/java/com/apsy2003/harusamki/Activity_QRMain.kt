package com.apsy2003.harusamki

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.apsy2003.harusamki.databinding.ActivityMainBinding
import com.apsy2003.harusamki.databinding.ActivityQrmainBinding
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Activity_QRMain : AppCompatActivity() {
    private lateinit var binding: ActivityQrmainBinding
    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>

    private val PERMISSIONS_REQUEST_CODE = 1 // 1)
    private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.CAMERA) // 2)카메라 권한 지정

    private var isDetected= false

    override fun onResume(){
        super.onResume()
        isDetected= false
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrmainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (!hasPermissions(this)) {
            // 카메라 권한을 요청합니다.
            requestPermissions(PERMISSIONS_REQUIRED, PERMISSIONS_REQUEST_CODE)
        } else {
            // 만약 이미 권한이 있다면 카메라를 시작합니다.
            startCamera()
        }

        //되돌아가기 버튼
        val Back = Intent(this, Activity_indexmain::class.java)
        binding.Timerback.setOnClickListener{ startActivity(Back)}
    }

    fun hasPermissions(context: Context) = PERMISSIONS_REQUIRED.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    //권한요청 콜백함수
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (PackageManager.PERMISSION_GRANTED == grantResults.firstOrNull()) {
                Toast.makeText(this@Activity_QRMain, "권한 요청이 승인되었습니다.", Toast.LENGTH_LONG).show()
                startCamera()
            } else {
                Toast.makeText(this@Activity_QRMain, "권한 요청이 거부되었습니다.", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }


    //미리보기와 이미지 분석을 시작
    fun startCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            val preview = getPreview()
            val imageAnalysis = getImageAnalysis()
            //후면카메라 선택
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            //미리보기 기능 선택
            cameraProvider.bindToLifecycle(this, cameraSelector,preview,imageAnalysis)

        }, ContextCompat.getMainExecutor(this))

    }

    fun getImageAnalysis(): ImageAnalysis {

        val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
        val imageAnalysis = ImageAnalysis.Builder().build()

        //  Analyzer를 설정합니다.
        imageAnalysis.setAnalyzer(cameraExecutor, QRCodeAnalyzer(object : OnDetectListener {
            override fun onDetect(msg: String) {
                if (!isDetected) {
                    isDetected = true // 데이터가 감지가 되었으므로 true로 바꾸어줍니다.

                    val intent = Intent(this@Activity_QRMain, ResultActivity::class.java)
                    intent.putExtra("msg", msg)
                    startActivity(intent)
                }
            }
        }))

        return imageAnalysis
    }

    //미리보기 객체를 반환
    fun getPreview(): Preview { //Preview 객체 생성
        val preview : Preview = Preview.Builder().build()
        preview.setSurfaceProvider(binding.barcodePreview.getSurfaceProvider())

        return preview
    }

}