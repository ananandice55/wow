package buu.informatics.s59160143.wow


import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160143.wow.database.PeopleDatabaseModel
import buu.informatics.s59160143.wow.databinding.FragmentInsertBinding
import buu.informatics.s59160143.wow.databinding.FragmentListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

/**
 * A simple [Fragment] subclass.
 */
class InsertFragment : Fragment() {
    private lateinit var binding: FragmentInsertBinding
    private lateinit var people: ViewModel
    var pictureUrl = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_insert, container, false
        )

        return binding.root
    }
    private fun submit() {
        binding.btnAdd.setOnClickListener {
            //uploadImage()
            if (!binding.nameEditText.text.isEmpty() && !binding.ageEditText.text.isEmpty() && !binding.proportionEdittext.text.isEmpty()
                && !binding.placeEditText.text.isEmpty() && !binding.priceEditText.text.isEmpty() && !binding.contactEditText.text.isEmpty()) {
                Handler().postDelayed({
                    people.insert(
                        PeopleDatabaseModel(
                            0,
                            pictureUrl,
                            binding.nameEditText.text.toString(),
                            binding.ageEditText.text.toString(),
                            binding.proportionEdittext.text.toString(),
                            binding.placeEditText.text.toString(),
                            binding.priceEditText.text.toString(),
                            binding.contactEditText.text.toString()
                        )
                    )
                    Toast.makeText(getActivity(), "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_LONG).show()
                    view?.findNavController()
                        ?.navigate(R.id.action_InsertFragment_to_mainFragment)
                }, 2000)
            } else {
                Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show()
            }

        }
    }

}
