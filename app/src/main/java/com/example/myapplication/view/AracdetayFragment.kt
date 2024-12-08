package com.example.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentAracdetayBinding
import com.example.myapplication.model.Araba
import com.example.myapplication.util.gorselIndir
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class aracdetayFragment : Fragment() {
    private var selectedProduct: Araba? = null
    private var _binding: FragmentAracdetayBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: AracdetayViewModel
    var arabaId=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAracdetayBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this)[AracdetayViewModel::class.java]
        arguments?.let {
            arabaId=aracdetayFragmentArgs.fromBundle(it).arabaId
selectedProduct=Args
        }
        viewModel.roomVerisiniAl(arabaId)
        observeLiveData()
        binding.sepeteEklebutton.setOnClickListener { addToCart() }

    }
    fun addToCart(){
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        if (userId == null){
            Toast.makeText(context,"Kullanıcı Oturumu Açık Değil",Toast.LENGTH_SHORT).show()
            return
        }

        val cartReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Cart").child(userId)

        selectedProduct?.let{ product ->
            cartReference.child(product.id ?: "").setValue(product)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Ürün sepete eklendi.", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { error ->
                    Toast.makeText(requireContext(), "Hata: ${error.message}", Toast.LENGTH_SHORT).show()
                }
        } ?: run {
            Toast.makeText(context,"Ürün Bilgisi Alınamadı.",Toast.LENGTH_SHORT).show()
        }

    }
    private fun observeLiveData(){
        viewModel.arabaLiveData.observe(viewLifecycleOwner){
            binding.markatextView.text= "Araç markası:"+it.marka
            binding.modeltextView.text="Araç modeli:"+it.model
            binding.fiyattextView.text="Araç fiyatı:"+it.fiyat+"TL"
            binding.yakTtextView.text="Araç yakıt türü:"+it.yakit
            binding.motorGucutextView.text="Araç motor silindiri:"+it.motorGuc+"cc"
            binding.hptextView.text="Araç beygiri:"+it.hp+"hp"
            binding.carImage.gorselIndir(it.gorsel)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}