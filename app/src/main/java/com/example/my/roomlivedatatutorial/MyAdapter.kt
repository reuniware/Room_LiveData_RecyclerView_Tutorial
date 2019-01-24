package com.example.my.roomlivedatatutorial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

    class MyAdapter(val userList: List<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            val userName: TextView = view.findViewById(R.id.textView1)

            fun bindItems(item: User) {
                userName.setText(item.id.toString() + " " + item.firstName + " " + item.lastName)
                itemView.setOnClickListener {
                    Toast.makeText(itemView.context, item.firstName + " " + " is Clicked", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_row, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bindItems(userList.get(position));
        }

        override fun getItemCount(): Int {
            return userList.size
        }


    }


