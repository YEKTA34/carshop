package com.example.myapplication
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.atilsamancioglu.besinkitabi.viewmodel.ArabaListesiViewmodel
import com.example.myapplication.R
import com.example.myapplication.adapter.ArabaRecyclerAdapter
import com.example.myapplication.databinding.FragmentAracListeBinding


import com.example.myapplication.databinding.FragmentFeedBinding
import com.example.myapplication.model.Araba


class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private val recyclerArabaAdapter = ArabaRecyclerAdapter(arrayListOf())
    private lateinit var viewmodel: ArabaListesiViewmodel
    private val arabaRecyclerAdapter=ArabaRecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel= ViewModelProvider(this)[ArabaListesiViewmodel::class.java]
        viewmodel.refreshData()
        binding.aracRecyclerView.layoutManager=LinearLayoutManager(context)
        binding.aracRecyclerView.adapter = arabaRecyclerAdapter
        val profileIcon = view.findViewById<ImageView>(R.id.profileIcon)
        val cartIcon = view.findViewById<ImageView>(R.id.cartIcon)
        binding.cartIcon.setOnClickListener {sepeteGit(it)  }
        binding.profileIcon.setOnClickListener { profileGit(it) }
        observeLiveData()

    }
    fun sepeteGit(view: View){

        val action=FeedFragmentDirections.actionFeedFragmentToSepetFragment()

        Navigation.findNavController(view).navigate(action)
    }
    fun profileGit(view: View){
        val action=FeedFragmentDirections.actionFeedFragmentToProfilFragment()
        Navigation.findNavController(view).navigate(action)
    }

    fun observeLiveData() {
        viewmodel.arabalar.observe(viewLifecycleOwner){
            arabaRecyclerAdapter.arabaListesiniGuncelle(it)
            binding.aracRecyclerView.visibility=View.VISIBLE
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}
