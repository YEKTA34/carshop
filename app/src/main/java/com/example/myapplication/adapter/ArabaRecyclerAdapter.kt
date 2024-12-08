package com.example.myapplication.adapter

import android.text.Layout.Directions
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.FeedFragmentDirections
import com.example.myapplication.databinding.AracRecyclerRowBinding
import com.example.myapplication.databinding.RecyclerRowBinding
//import com.example.myapplication.FeedFragmentDirections
import com.example.myapplication.model.Araba
import com.example.myapplication.oneFragmentDirections
import com.example.myapplication.util.gorselIndir


class ArabaRecyclerAdapter(val arabaListesi:ArrayList<Araba>):RecyclerView.Adapter<ArabaRecyclerAdapter.ArabaViewHolder>(){
    class ArabaViewHolder(val binding:AracRecyclerRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArabaViewHolder {
        val binding=AracRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArabaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arabaListesi.size
    }
    fun arabaListesiniGuncelle(yeniArabaListesi:List<Araba>){
        arabaListesi.clear()
        arabaListesi.addAll(yeniArabaListesi)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ArabaViewHolder, position: Int) {
        holder.binding.marka.text=arabaListesi[position].marka
        holder.binding.model.text=arabaListesi[position].model
        holder.itemView.setOnClickListener {it
            val action=FeedFragmentDirections.actionFeedFragmentToAracdetayFragment2(arabaListesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)

        }
        holder.binding.imageView.gorselIndir(arabaListesi[position].gorsel)
    }
}
