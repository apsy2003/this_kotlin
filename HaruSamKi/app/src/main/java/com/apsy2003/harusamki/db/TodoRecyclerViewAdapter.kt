package com.apsy2003.harusamki.db

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apsy2003.harusamki.OnItemLongClickListener
import com.apsy2003.harusamki.R
import com.apsy2003.harusamki.databinding.ItemTodoBinding


class TodoRecyclerViewAdapter(private val todoList: ArrayList<ToDoEntity>, private val listener : OnItemLongClickListener) :
    RecyclerView.Adapter<TodoRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        val tv_importance = binding.tvImportance
        val tv_title = binding.tvTitle

        // 뷰 바인딩에서 기본적으로 제공하는 root 변수는 레이아웃의 루트 레이아웃을 의미
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // item_todo.xml 바인딩 객체 생성
        val binding: ItemTodoBinding =
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // 중요도에 따라 색상 변경

        val todoData = todoList[position]
        // 중요도에 따라 색상 변경
        when (todoData.importance) {
            1 -> {
                holder.tv_importance.setBackgroundResource(R.color.red)
            }

            2 -> {
                holder.tv_importance.setBackgroundResource(R.color.yellow)
            }

            3 -> {
                holder.tv_importance.setBackgroundResource(R.color.green)
            }
        }
        holder.root.setOnLongClickListener {
            listener.onLongClick(position)
            false
        }
        // 중요도에 따라 중요도 변경
        holder.tv_importance.text = todoData.importance.toString()
        // 할 일 제목 변경
        holder.tv_title.text = todoData.title
    }

    override fun getItemCount(): Int {
        // 리사이클러뷰 아이템 개수는 할 일 리스트의 크기
        return todoList.size
    }

}