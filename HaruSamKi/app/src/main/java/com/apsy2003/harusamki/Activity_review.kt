package com.apsy2003.harusamki

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.apsy2003.harusamki.databinding.ActivityReviewBinding
import java.io.File

class Activity_review : AppCompatActivity() {
        var photoUri: Uri? = null

        lateinit var cameraPermission: ActivityResultLauncher<String>
        lateinit var storagePermission: ActivityResultLauncher<String>
        lateinit var cameraLauncher: ActivityResultLauncher<Uri>
        lateinit var galleryLauncher: ActivityResultLauncher<String>

        val binding by lazy{ ActivityReviewBinding.inflate(layoutInflater)}

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(binding.root)

            storagePermission = registerForActivityResult(
                ActivityResultContracts
                .RequestPermission()) { isGranted ->
                if(isGranted) {
                    setViews()
                } else {
                    Toast.makeText(baseContext, "외부저장소 권한을 승인해야 앱을 사용할 수 있습니다.",
                        Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            cameraPermission = registerForActivityResult(
                ActivityResultContracts
                .RequestPermission()) { isGranted ->
                if(isGranted) {
                    openCamera()
                } else {
                    Toast.makeText(baseContext, "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.",
                        Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) {
                    isSuccess  ->
                if(isSuccess) { binding.imagePreview.setImageURI(photoUri) }
            }

            galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){ uri ->
                binding.imagePreview.setImageURI(uri)
            }

            storagePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)

            //되돌아가기 버튼
            val Back = Intent(this, Activity_Community::class.java)
            binding.iconBox.setOnClickListener{ startActivity(Back)}

            //햄버거 메뉴 링크 연결
            val hamMenu = Intent(this, Activity_hambuger::class.java)
            binding.head4.setOnClickListener{ startActivity(hamMenu)}

            //하단 footer메뉴 링크 연결
            val Footmenu1 = Intent(this, Activity_review::class.java)
            binding.fmenu1.setOnClickListener{ startActivity(Footmenu1)}

            val Footmenu2 = Intent(this, Activity_Community::class.java)
            binding.fmenu2.setOnClickListener{ startActivity(Footmenu2)}

            val Footmenu3 = Intent(this, Activity_indexmain::class.java)
            binding.fmenu3.setOnClickListener{ startActivity(Footmenu3)}

            val Footmenu4 = Intent(this, Activity_mypage::class.java)
            binding.fmenu4.setOnClickListener{ startActivity(Footmenu4)}

            val Footmenu5 = Intent(this, Activity_setting::class.java)
            binding.fmenu5.setOnClickListener{ startActivity(Footmenu5)}
        }

        fun setViews() {
            binding.buttonCamera.setOnClickListener {
                cameraPermission.launch(Manifest.permission.CAMERA)
            }

            binding.buttonGallery.setOnClickListener{
                openGallery()
            }
        }

        fun openCamera() {
            val photoFile = File.createTempFile(
                "IMG_",
                ".jpg",
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            )
            photoUri = FileProvider.getUriForFile(
                this,
                "${packageName}.provider",
                photoFile
            )

            cameraLauncher.launch(photoUri)
        }

        fun openGallery(){
            galleryLauncher.launch("image/*")
        }
}
