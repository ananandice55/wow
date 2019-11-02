package buu.informatics.s59160143.wow


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import buu.informatics.s59160143.wow.databinding.FragmentInsertBinding

/**
 * A simple [Fragment] subclass.
 */
class InsertFragment : Fragment() {
    private val people: ArrayList<People> = ArrayList<People>()
    private  lateinit var binding: FragmentInsertBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_insert,container,false)
        for (i in 0..2) {
            people.add(i, People("", "", "","","",""))
        }
        binding.btnAdd.setOnClickListener {
            clickAdd()
        }
        return binding.root
    }
    fun clickAdd() {
        var name = binding.editText
        var age = binding.editText2
        var proportion = binding.editText3
        var place = binding.editText4
        var price = binding.editText5
        var contact = binding.editText6

//        name.text = null
//        age.text = null
//        name.text = null
//        proportion.text = null
//        place.text = null
//        price.text = null
//        contact.text = null


        var showName = name.text
        var showAge = age.text
        var showProportion = proportion.text
        var showPlace = place.text
        var showPrice = price.text
        var showContact = contact.text

        binding.btnAdd.setOnClickListener {
            people.add(
                0,
                People(
                    showName.toString(),
                    showAge.toString(),
                    showProportion.toString(),
                    showPlace.toString(),
                    showPrice.toString(),
                    showContact.toString()
                )
            )
            Log.i("test","${people.size}")
        }
    }
}
