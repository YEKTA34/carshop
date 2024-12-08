package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.myapplication.databinding.FragmentOneBinding
import com.example.myapplication.databinding.FragmentYanlisBinding


class yanlisFragment : Fragment() {
    private var _binding: FragmentYanlisBinding? = null
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
        _binding = FragmentYanlisBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.geridonButton.setOnClickListener { geriDon(it) }

    }
    fun geriDon(view: View){
        val action=yanlisFragmentDirections.actionYanlisFragmentToAdminFragment()
        Navigation.findNavController(view).navigate(action)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
    }
