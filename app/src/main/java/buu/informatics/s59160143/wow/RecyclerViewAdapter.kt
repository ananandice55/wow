package buu.informatics.s59160143.wow

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var picasso = Picasso.get()
    var data = listOf<People>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.textViewPeople)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewPeople)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.people_list, parent, false)
                return ViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data[position]
        holder.bind(item)
    }

    private fun ViewHolder.bind(item: People) {
        nameText.text = item.name
        imageView.setImageURI(Uri.parse(item.image))
        picasso.load(item.image).into(imageView)

    }

}

