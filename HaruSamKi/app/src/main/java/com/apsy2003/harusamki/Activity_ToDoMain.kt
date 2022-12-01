package com.apsy2003.harusamki

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.apsy2003.harusamki.databinding.ActivityToDoMainBinding
import com.apsy2003.harusamki.db.AppDatabase
import com.apsy2003.harusamki.db.ToDoDao
import com.apsy2003.harusamki.db.ToDoEntity
import com.apsy2003.harusamki.db.TodoRecyclerViewAdapter

class Activity_ToDoMain : AppCompatActivity(), OnItemLongClickListener {
    private lateinit var binding: ActivityToDoMainBinding

    private lateinit var db: AppDatabase
    private lateinit var todoDao: ToDoDao
    private lateinit var todoList: ArrayList<ToDoEntity>
    private lateinit var adapter: TodoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //로고버튼
        val logo = Intent(this, Activity_indexmain::class.java)
        binding.logobox.setOnClickListener{ startActivity(logo)}

        //되돌아가기 버튼
        val Back = Intent(this, Activity_hambuger::class.java)
        binding.iconBox.setOnClickListener{ startActivity(Back)}

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
        
        //할일작성하기 페이지 링크 연결

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)
        }

        db = AppDatabase.getInstance(this)!!
        todoDao = db.getTodoDao()

        getAllTodoList() // 할 일 리스트 가져오기
    }

    private fun getAllTodoList() {
        Thread {
            todoList = ArrayList(todoDao.getAll())
            setRecyclerView()
        }.start()
    }

    private fun setRecyclerView() {
        // 리사이클러뷰 설정
        runOnUiThread {
            adapter = TodoRecyclerViewAdapter(todoList, this) // 어댑터 객체 할당
            binding.recyclerView.adapter = adapter // 리사이클러뷰 어댑터로 위에서 만든 어댑터 설정
            binding.recyclerView.layoutManager = LinearLayoutManager(this) // 레이아웃 매니저 설정
        }
    }

    override fun onRestart() {
        super.onRestart()
        getAllTodoList()
    }

    override fun onLongClick(position: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("할 일 삭제")
        builder.setMessage("정말 삭제하시겠습니까?")
        builder.setNegativeButton("취소", null)
        builder.setPositiveButton("네",
            object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    deleteTodo(position)
                }
            }
        )
        builder.show()
    }

    private fun deleteTodo(position: Int) {
        Thread {
            todoDao.deleteTodo(todoList[position]) // DB에서 삭제
            todoList.removeAt(position) // 리스트에서 삭제
            runOnUiThread { // UI 관련 작업은 UI 스레드에서
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }




}