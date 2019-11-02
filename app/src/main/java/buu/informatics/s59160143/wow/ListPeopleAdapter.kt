package buu.informatics.s59160143.wow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class ListPeopleAdapter(var context: Context?, var type: ArrayList<PeopleListData>): BaseAdapter() {
    private class ViewHolder(row: View?){
        var name: TextView
        var image: ImageView

        init {
            this.name = row?.findViewById(R.id.textViewPeople) as TextView
            this.image = row?.findViewById(R.id.imageViewPeople) as ImageView
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder: ViewHolder
        if(convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.people_list,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var people:PeopleListData = getItem(position) as PeopleListData
        viewHolder.name.text = people.name
        viewHolder.image.setImageResource(people.image)
        return view as View

    }

    override fun getItem(position: Int): Any {
        return type.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return type.count()
    }
}