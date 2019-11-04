package buu.informatics.s59160143.wow


import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import buu.informatics.s59160143.wow.database.PeopleDatabaseModel
import buu.informatics.s59160143.wow.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false
        )
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        viewModel.peopleAll.observe(this, Observer { it ->
            if (it.isEmpty()) {
                Log.i("test", "empty")
                setPeople()
            }
        })


        return binding.root
    }

    private fun setPeople() {
        var array: ArrayList<People> = ArrayList()
        array.add(
            People(
                "น้องฟ้า",
                "23",
                "34-24-35",
                "บางแสน",
                "1500-2000",
                "line:@ssss",
                "https://firebasestorage.googleapis.com/v0/b/wowfirebaseproject-7a66d.appspot.com/o/uploads%2Fimgtwo.jpg?alt=media&token=486f1596-a430-4b9d-b15f-38339678141f"
            )
        )
        array.add(
            People(
                "น้องปลาย",
                "24",
                "34-24-35",
                "บางแสน",
                "1500-2000",
                "line:@ssss",
                "https://firebasestorage.googleapis.com/v0/b/wowfirebaseproject-7a66d.appspot.com/o/uploads%2Fimgthree.jpg?alt=media&token=78cb987f-5d23-48ca-bb28-8f97c08a0283"
            )
        )
        array.add(
            People(
                "น้องหญิง",
                "25",
                "34-24-35",
                "บางแสน",
                "1500-2000",
                "line:@ssss",
                "https://firebasestorage.googleapis.com/v0/b/wowfirebaseproject-7a66d.appspot.com/o/uploads%2Fimgfour.jpg?alt=media&token=47310aa5-8b18-41ff-9517-d21ae3f7b22b"
            )
        )
        array.add(
            People(
                "น้องหนาว",
                "26",
                "34-24-35",
                "บางแสน",
                "1500-2000",
                "line:@ssss",
                "https://firebasestorage.googleapis.com/v0/b/wowfirebaseproject-7a66d.appspot.com/o/uploads%2Fimgfive.jpg?alt=media&token=ddd93d56-a3a9-4f28-a50e-46815c951907"
            )
        )

        array.forEach {
            viewModel.insert(
                PeopleDatabaseModel(
                    it.name,
                    it.age,
                    it.proportion,
                    it.place,
                    it.price,
                    it.contact,
                    it.image
                )
            )
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        )
                || super.onOptionsItemSelected(item)
    }

}
