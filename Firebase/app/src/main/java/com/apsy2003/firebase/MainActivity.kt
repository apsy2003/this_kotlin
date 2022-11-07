package com.apsy2003.firebase

import android.icu.number.NumberFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.apsy2003.firebase.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val database = Firebase.database("https://calm-cascade-367500-default-rtdb.asia-southeast1.firebasedatabase.app")
    val myRef = database.getReference("users")

    val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fun addItem(user:User){
            val id = myRef.push().key!!
            user.id = id
            myRef.child(id).setValue(user)
        }
        with(binding){
            btnPost.setOnClickListener{
                val name = editName.text.toString()
                val password = editPassword.text.toString()
                val user = User(name, password)
                addItem(user)
            }
        }
        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                binding.textList.setText("")//먼저 textList를 지운다.
                //snapshot 안에 있는 실제 데이터를 반복문으로 꺼낼 수 있다.
                for (item in snapshot.children){
                    /*item의 value를 꺼내서 User 클래스로 캐스팅한다.
                      value가 없을 수도 있기 때문에 let 스코프 함수로 처리한다.*/
                    item.getValue(User::class.java)?.let{user ->
                        binding.textList.append("${user.name} : ${user.password} \n")
                    }
                }
            }
            override fun onCancelled(error: DatabaseError){
                print(error.message)
            }
        })
    }
}

class User{
    var id: String = ""
    var name: String = ""
    var password: String = ""

    constructor() // 파이어베이스에서 데이터 변환을 위해서 필요

    constructor(name:String, password:String){
        this.name = name
        this.password = password
    }
}