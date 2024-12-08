package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentArabaDetayBinding
import com.example.myapplication.databinding.FragmentAracdetayBinding
import com.example.myapplication.databinding.FragmentFeedBinding


class ArabaDetayFragment : Fragment() {
    private var _binding: FragmentArabaDetayBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArabaDetayBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onDestroy() {
        super.onDestroy()
    }

}