package buu.informatics.s59160143.wow


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160143.wow.databinding.FragmentListBinding


/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list, container, false
        )
        var array: ArrayList<PeopleListData> = setBrand()

        binding.listViewData.adapter = ListPeopleAdapter(getActivity()?.applicationContext, array);

        binding.listViewData.setOnItemClickListener { parent, view, position, id ->
            var brandSelected = bundleOf("people" to array[position].name)
            view.findNavController()
                .navigate(R.id.action_listFragment_to_detailFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
    }
    private fun setBrand(): ArrayList<PeopleListData> {
        var array: ArrayList<PeopleListData> = ArrayList()
        array.add(PeopleListData("น้องฟ้า", R.drawable.imgtwo));
        array.add(PeopleListData("น้องปลาย", R.drawable.imgthree));
        array.add(PeopleListData("น้องหญิง", R.drawable.imgfour));
        array.add(PeopleListData("น้องหนาว", R.drawable.imgfive));
        return array
    }

}