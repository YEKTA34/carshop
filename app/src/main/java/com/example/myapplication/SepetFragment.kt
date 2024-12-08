package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.myapplication.FeedFragmentDirections
import com.example.myapplication.databinding.FragmentSepetBinding
import com.example.myapplication.adapter.ArabaRecyclerAdapter
import com.example.myapplication.model.Araba


class SepetFragment : Fragment() {

    private lateinit var binding: FragmentSepetBinding
    private lateinit var viewModel: SepetViewModel
    private lateinit var adapter: ArabaRecyclerAdapter
    private var arabaListesi: ArrayList<Araba> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSepetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel'i al
        viewModel = ViewModelProvider(this).get(SepetViewModel::class.java)

        // Adapter'ı ayarla


        // RecyclerView'a adapter'ı bağla
        binding.recyclerView.adapter = adapter

        // ViewModel'den araba listesini al
        observeLiveData()

        // Verileri ViewModel'den almak için çağırma
viewModel.arabaListesi
    }

    private fun observeLiveData() {
        viewModel.arabaListesi.observe(viewLifecycleOwner) { arabalar ->
            arabaListesi.clear()
            arabaListesi.addAll(arabalar)
            adapter.notifyDataSetChanged()
        }
    }
}
