package com.example.taskfourr

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfourr.model.Contact

class Adapter(var context: Context,var contactList : ArrayList<Contact>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    lateinit var dialog: Dialog

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sp = context.getSharedPreferences("user",Context.MODE_PRIVATE)
        val ed = sp.edit()
        val user:Contact = contactList[position]
        ed.putInt("index",position)
        ed.putString("name",user.name)
        ed.putString("number",user.number)
        holder.bind(user)
        holder.delete.setOnClickListener {
            (context as MainActivity).updateArray(user)
        }
        holder.edit.setOnClickListener {
            (context as MainActivity).updateUi(user,position)

        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name:TextView = itemView.findViewById(R.id.name)
        var number:TextView = itemView.findViewById(R.id.number)
        var delete:ImageButton = itemView.findViewById(R.id.delete)
        var edit:ImageButton = itemView.findViewById(R.id.edit)

        fun bind(user:Contact){
            name.text = user.name
            number.text = user.number


        }
    }
}


