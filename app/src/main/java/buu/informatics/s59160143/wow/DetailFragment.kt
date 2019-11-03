package buu.informatics.s59160143.wow


import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import buu.informatics.s59160143.wow.databinding.FragmentDetailBinding
import buu.informatics.s59160143.wow.databinding.FragmentListBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.people_list.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private var picasso = Picasso.get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        )
        setDetail()
        return binding.root
    }

    private fun setDetail() {
        val getDetail = arguments?.getString("people")!!.split(" ")
        val getImage = arguments?.getString("peopleimage")!!

//        Log.i("test","image${getDetail.get(6)}")
//
        binding.apply {
            nameTextView.text = getDetail.get(0)
            ageTextView.text = getDetail.get(1)
            proportionTextView.text = getDetail.get(2)
            placeTextView.text = getDetail.get(3)
            priceTextView.text = getDetail.get(4)
            contactTextView.text = getDetail.get(5)
            detailImage.setImageURI(Uri.parse(getImage))
            picasso.load(getImage).into(detailImage)
        }
    }


}
