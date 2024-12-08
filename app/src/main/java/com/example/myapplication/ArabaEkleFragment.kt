import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myapplication.databinding.FragmentArabaEkleBinding
import com.example.myapplication.model.Araba
import com.example.myapplication.roomdb.ArabaDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArabaEkleFragment : Fragment() {

    private lateinit var binding: FragmentArabaEkleBinding
    private lateinit var arabaDatabase: ArabaDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArabaEkleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arabaDatabase = ArabaDatabase.invoke(requireContext())

        binding.ekleButton.setOnClickListener {
            val marka = binding.markaEditText.text.toString()
            val model = binding.modelEditText.text.toString()
            val fiyat = binding.fiyatEditText.text.toString()
            val gorsel = binding.gorselUrlEditText.text.toString()
            val motorGuc=binding.motorGucEditText.text.toString()
            val hp=binding.hpEditText.text.toString()
            val yakit=binding.yakitEditText.text.toString()

            val yeniAraba = Araba(
                gorsel = gorsel,
                fiyat = fiyat,
                motorGuc = motorGuc, // Varsayılan değerler
                yakit = yakit,
                hp = hp,
                marka = marka,
                model = model
            )

            // Veritabanına kaydet
            CoroutineScope(Dispatchers.IO).launch {
                arabaDatabase.arabaDao().insertAll(yeniAraba)
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Araba eklendi", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view).popBackStack()
                }
            }
        }
    }
}
