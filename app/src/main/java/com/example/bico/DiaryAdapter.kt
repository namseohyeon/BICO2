package com.example.bico

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bico.databinding.ItemDiaryBinding

class DiaryViewHolder(val binding: ItemDiaryBinding) : RecyclerView.ViewHolder(binding.root)
class DiaryAdapter(val context: Context, val itemList: MutableList<ItemData>):RecyclerView.Adapter<DiaryViewHolder>() {
    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return itemList.size
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val data = itemList.get(position)
        holder.binding.run{
            itemEmailView.text = data.email
            itemDateView.text = data.date
            itemContentView.text = data.content
        }
        val imageRef = loginApplication.storage.reference.child("images/${data.docId}.jpg")
        imageRef.downloadUrl.addOnCompleteListener{task->
            if(task.isSuccessful){
                Glide.with(context)
                    .load(task.result)
                    .into(holder.binding.itemImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        //TODO("Not yet implemented")
        val layoutInflater = LayoutInflater.from(parent.context)
        return DiaryViewHolder(ItemDiaryBinding.inflate(layoutInflater))
    }

}