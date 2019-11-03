package buu.informatics.s59160143.wow


import android.content.ActivityNotFoundException
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import buu.informatics.s59160143.wow.databinding.FragmentDetailBinding
import buu.informatics.s59160143.wow.databinding.FragmentListBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
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
        setHasOptionsMenu(true)
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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (menu != null) {
            if (inflater != null) {
                super.onCreateOptionsMenu(menu, inflater)
            }
        }
        inflater?.inflate(R.menu.share, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareMenu -> onShare()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun onShare() {
        val shareIntent = ShareCompat.IntentBuilder.from(getActivity())
            .setText(nameTextView.text.toString())
            .setType("text/plain")
            .intent
        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {

        }
    }



}
