package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.SepetArabaRowBinding
import com.example.myapplication.model.SepetAraba

class SepetArabaAdapter(private val sepetArabaListesi: ArrayList<SepetAraba>) :
    RecyclerView.Adapter<SepetArabaAdapter.SepetArabaViewHolder>() {

    class SepetArabaViewHolder(val binding: SepetArabaRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetArabaViewHolder {
        val binding = SepetArabaRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SepetArabaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SepetArabaViewHolder, position: Int) {
        val araba = sepetArabaListesi[position]
        holder.binding.markaTextView.text = araba.marka
        holder.binding.modelTextView.text = araba.model
        holder.binding.fiyatTextView.text = araba.fiyat
    }

    override fun getItemCount(): Int {
        return sepetArabaListesi.size
    }
}

