package com.example.myapplication

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.FragmentIlanYukleBinding
import com.example.myapplication.databinding.FragmentKullaniciBinding
import com.google.android.material.snackbar.Snackbar


class ilanYukleFragment : Fragment() {
    private var _binding: FragmentIlanYukleBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private lateinit var activityResultLauncher:ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher:ActivityResultLauncher<String>
    var secilenGorsel: Uri?=null
    var secilenBitmap:Bitmap?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
registerLaunchers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIlanYukleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ilanYuklebutton.setOnClickListener { yukleTiklandi(it) }
        binding.imageView11.setOnClickListener { fotoYukle(it) }
    }
    fun yukleTiklandi(view: View){


    }
    fun fotoYukle(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_MEDIA_IMAGES
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        requireActivity(),
                        Manifest.permission.READ_MEDIA_IMAGES
                    )
                ) {
                    Snackbar.make(
                        view,
                        "galeriye gitmek için izin verin",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("izin ver", View.OnClickListener { permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)}).show()

            } else {
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }
        } else {
                val intentToGallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
    }else{
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        requireActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    Snackbar.make(
                        view,
                        "galeriye gitmek için izin verin",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("izin ver", View.OnClickListener { permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)}).show()

                } else {
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            } else {
                val intentToGallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }

        }
    }
    private fun registerLaunchers(){
activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
if (result.resultCode==RESULT_OK){
    val intentFromResult=result.data
    if (intentFromResult!=null){
        secilenGorsel=intentFromResult.data
        try {
if (Build.VERSION.SDK_INT>=28){
    val source=ImageDecoder.createSource(requireActivity().contentResolver,secilenGorsel!!)
    secilenBitmap=ImageDecoder.decodeBitmap(source)
    binding.imageView11.setImageBitmap(secilenBitmap)

}else{
    secilenBitmap=MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,secilenGorsel)
    binding.imageView11.setImageBitmap(secilenBitmap)
}
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}
}
        permissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()){result->
            if (result){
val intentToGallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }else{
Toast.makeText(requireContext(),"izni reddettiniz izninize ihtiyacımız var",Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}