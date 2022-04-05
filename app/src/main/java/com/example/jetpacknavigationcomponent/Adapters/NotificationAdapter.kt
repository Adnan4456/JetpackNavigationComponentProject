package com.example.jetpacknavigationcomponent.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpacknavigationcomponent.databinding.ItemViewBinding

class NotificationAdapter(val myList: List<String>): RecyclerView.Adapter<NotificationAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        //This simple view
//        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.item_view , parent,false)
//        return MyViewHolder(view)

        //With view binding
        return MyViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent , false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.itemTxtView.text = myList[position]

    }

    override fun getItemCount(): Int {
       return myList.size
    }

    class MyViewHolder(val binding: ItemViewBinding):RecyclerView.ViewHolder(binding.root){


    }

}