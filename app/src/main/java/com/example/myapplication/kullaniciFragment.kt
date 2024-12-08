package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.myapplication.databinding.FragmentKullaniciBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class kullaniciFragment : Fragment() {
    private var _binding: FragmentKullaniciBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

auth= Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKullaniciBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
binding.kayitbutton.setOnClickListener { kayitol(it) }
        binding.girisButton.setOnClickListener { girisyap(it) }
        
    }
    fun kayitol(view: View){
        val email=binding.emailText.text.toString()
        val password=binding.passwordtext.text.toString()
        if (email.isNotEmpty()&&password.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
                if (task.isSuccessful){
                    val action=kullaniciFragmentDirections.actionKullaniciFragmentToFeedFragment()
                    Navigation.findNavController(view).navigate(action)
                }
            }.addOnFailureListener { exception->
                Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }
    fun girisyap(view: View){
        val email=binding.emailText.text.toString()
        val password=binding.passwordtext.text.toString()
        if (email.isNotEmpty()&&password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val action=kullaniciFragmentDirections.actionKullaniciFragmentToFeedFragment()
                Navigation.findNavController(view).navigate(action)
            }.addOnFailureListener { exception->
                Toast.makeText(requireContext(),exception.localizedMessage , Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
}
}



