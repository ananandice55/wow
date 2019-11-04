package buu.informatics.s59160143.wow


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160143.wow.database.PeopleDatabaseModel
import buu.informatics.s59160143.wow.databinding.FragmentListBinding


/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list, container, false
        )
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)


        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val array: ArrayList<People> = getPeople()
        binding.recyclerViewPeople.addOnItemClickListener(object :
            OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                view.findNavController()
                    .navigate(
                        ListFragmentDirections.actionListFragmentToDetailFragment(
                            array[position].name + " " +
                                    array[position].age + " " +
                                    array[position].proportion + " " +
                                    array[position].place + " " +
                                    array[position].price + " " +
                                    array[position].contact, array[position].image

                        )
                    )
            }
        })
    }



    private fun getPeople(): ArrayList<People> {
        var array: ArrayList<People> = ArrayList()
        var count = 0

        viewModel.peopleAll.observe(this, Observer { it ->
            it.forEach {
                Log.i("test", it.peopleName)
                array.add(
                    People(
                        it.peopleName,
                        it.peopleAge,
                        it.peopleProportion,
                        it.peoplePlace,
                        it.peoplePrice,
                        it.peopleContact,
                        it.peopleImage
                    )
                )
                count++
            }
            if (count == it.size) {
                val adapter = RecyclerViewAdapter()
                binding.recyclerViewPeople.adapter = adapter
                adapter.data = array
                Log.i("test", "${array.size}")
                Log.i("test", "${adapter.data.size}")

            }


        })
        return array
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object :
            RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }
        })
    }
}