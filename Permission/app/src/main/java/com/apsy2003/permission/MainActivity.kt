package com.apsy2003.permission

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.apsy2003.permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    lateinit var activityResult: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        activityResult = registerForActivityResult(ActivityResultContracts.
            RequestPermission()){
                if(it){
                    startProcess()//승인이면 프로그램 진행
                }else{
                    finish() //미승인이면 앱종료
                }

        }

        binding.btnCamera.setOnClickListener{
            activityResult.launch(Manifest.permission.CAMERA)
        }
            //승인 처리 후 실행 할 코드를 입력합니다.
    }

    fun startProcess(){
        Toast.makeText(this, "카메라를 실행합니다.", Toast.LENGTH_LONG).show()
    }
}