package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.myapplication.databinding.FragmentFeedBinding
import com.example.myapplication.databinding.FragmentOneBinding


class oneFragment : Fragment() {
    private var _binding: FragmentOneBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOneBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adminButton.setOnClickListener { adminGiris(it) }
        binding.kullaniciButton.setOnClickListener { kullaniciGiris(it) }

    }
    fun adminGiris(view: View){
        val action=oneFragmentDirections.actionOneFragment2ToAdminFragment()
        Navigation.findNavController(view).navigate(action)
    }
    fun kullaniciGiris(view: View){
        val action=oneFragmentDirections.actionOneFragment2ToKullaniciFragment()
        Navigation.findNavController(view).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
    }