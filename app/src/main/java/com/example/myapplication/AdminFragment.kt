package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.myapplication.databinding.FragmentAdminBinding
import com.example.myapplication.databinding.FragmentKullaniciBinding


class adminFragment : Fragment() {
    private var _binding: FragmentAdminBinding? = null
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
        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adminGirisbutton.setOnClickListener { kullaniciAdi(it) }
    }
    fun kullaniciAdi(view: View){
        if (binding.KullaniciAdiText.text.toString()=="admin" &&binding.AdminsifreText.text.toString()=="admin123"){
            val action=adminFragmentDirections.actionAdminFragmentToIlanBakYukleFragment()
            Navigation.findNavController(view).navigate(action)
        }else{
            val action=adminFragmentDirections.actionAdminFragmentToYanlisFragment()
            Navigation.findNavController(view).navigate(action)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}
