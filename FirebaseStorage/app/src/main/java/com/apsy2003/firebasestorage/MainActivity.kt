package com.apsy2003.firebasestorage

import android.Manifest
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.apsy2003.firebasestorage.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MainActivity : AppCompatActivity() {

    //버킷에 연결하는 코드를 추가
    val storage = Firebase.storage("gs://harusamki-8f63d.appspot.com")
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnUpload.setOnClickListener{
            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        binding.btnDownload.setOnClickListener{
            //여기서는 개인 폴더에 있는 이미지 경로를 설정
            downloadImage("images/temp_1667958111044.jpeg")
        }
    }

    val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
        if(isGranted){
            //이미지 갤러리 런처 호출
            galleryLauncher.launch("image/*")
        } else{
            Toast.makeText(baseContext,
            "외부 저장소 읽기 권환을 승인해야 사용할 수 있습니다.",
            Toast.LENGTH_LONG).show()
        }
    }

    val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent())
    { uri ->
        uploadImage(uri)
    }

    fun downloadImage(path: String){
        //스토리지 레퍼런스를 연결하고 이미지url를 가져온다.
        storage.getReference(path).downloadUrl.addOnSuccessListener {uri ->
            Glide.with(this).load(uri).into(binding.imageView)
        }.addOnFailureListener{
            Log.e("스토리지", "다운로드 에러=>${it.message}")
        }
    }

    fun uploadImage(uri: Uri){
        //1. 경로 + 사용자ID+ 밀리초로 파일 주소 만들기
        val fullPath = makeFilePath("images", "temp", uri)
        //2. 스토리지에 저장할 경로 설정
        val imageRef = storage.getReference(fullPath)
        //3. 업로드 태스크 생성
        val uploadTask = imageRef.putFile(uri)

        //4. 업로드 실행 및 결과 확인
        uploadTask.addOnFailureListener{
            Log.d("스토리지", "실패=>${it.message}")
        }.addOnSuccessListener{taskSnapshot ->
            Log.d("스토리지", "성공 주소 =>${fullPath}")//5. 경로를 DB에 저장하고 사용
        }
    }

    fun makeFilePath(path:String, userId:String, uri:Uri): String{
        //마임타입 예) images/jpeg
        val mimeType = contentResolver.getType(uri)?:"/none"
        //확장자 예)jpeg
        val ext = mimeType.split("/")[1]
        //시간값 예)1232131241312
        val timeSuffix = System.currentTimeMillis()
        //완성
        val filename = "${path}/${userId}_${timeSuffix}.${ext}"
        //예) 경로/사용자ID_1232131241312.jpeg
        return filename
    }
}