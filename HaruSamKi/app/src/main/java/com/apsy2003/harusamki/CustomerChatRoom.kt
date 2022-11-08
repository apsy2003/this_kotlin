package com.apsy2003.harusamki

import android.app.Notification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.apsy2003.harusamki.databinding.ActivityCustomerChatRoomBinding
import com.apsy2003.harusamki.databinding.ItemMsgListBinding
import com.apsy2003.harusamki.model.Message
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CustomerChatRoom : AppCompatActivity() {
    val binding by lazy { ActivityCustomerChatRoomBinding.inflate(layoutInflater)}
    val database = Firebase.database("https://harusamki-8f63d-default-rtdb.asia-southeast1.firebasedatabase.app/")
    lateinit var msgRef: DatabaseReference

    var roomId: String ="" //방아이디
    var roomTitle: String = "" //방이름

    //<Message> model 패키지를 import 할 것!
    val msgList = mutableListOf<Message>() //파이어베이스에서 데이터를 불러온 후 저장할 변수
    lateinit var adapter: MsgListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //인텐트로 전달된 방 정보와 사용자 정보 꺼내기
        roomId = intent.getStringExtra("roomId") ?: "none"
        roomTitle = intent.getStringExtra("roomTitle") ?: "없음"
        //메세지 노드 레퍼런스 연결
        msgRef = database.getReference("rooms").child(roomId).child("messages")
        adapter = MsgListAdapter(msgList)
        with(binding){
            recyclerMsgs.adapter = adapter
            recyclerMsgs.layoutManager = LinearLayoutManager(baseContext)

            textTitle.setText(roomTitle)
            btnBack.setOnClickListener{finish()}
            btnSend.setOnClickListener{sendMsg()}
        }
        loadMsgs()
    }

    fun loadMsgs(){
        msgRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot){
                msgList.clear()
                for(item in snapshot.children){
                    item.getValue(Message::class.java)?.let { msg ->
                        msgList.add(msg)
                    }
                }
                //어댑터 갱신
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError){
                print(error.message)
            }
        })
    }
    fun sendMsg(){
        with(binding){
            if(editMsg.text.isNotEmpty()){
                val message = Message(editMsg.text.toString(), CustomerChatList.userName)
                val msgId = msgRef.push().key!!
                message.id = msgId
                msgRef.child(msgId).setValue(message)
                editMsg.setText("")
            }
        }
    }
}

class MsgListAdapter(val msgList:MutableList<Message>) : RecyclerView.Adapter<MsgListAdapter.Holder>(){
    //뷰 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemMsgListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return Holder(binding)
    }
    //바인딩 처리
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val msg = msgList.get(position)
        holder.setMsg(msg)
    }
    //목록의 개수
    override fun getItemCount(): Int {
        return msgList.size
    }

    class Holder(val binding: ItemMsgListBinding):
        RecyclerView.ViewHolder(binding.root){
        fun setMsg(msg:Message){
            binding.textName.setText(msg.userName)
            binding.textMsg.setText(msg.msg)
            binding.textDate.setText("${msg.timestamp}")
        }
    }
}