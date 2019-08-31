package com.example.newsfinal.Adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.widget.CheckBox
import com.example.newsfinal.Interface.Checkable
import com.example.newsfinal.R


class CustomAdapter(private var list: List<Checkable>?, val clickListener: (Checkable) -> Unit) : RecyclerView.Adapter<CustomAdapter.CheckBoxViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckBoxViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CheckBoxViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CheckBoxViewHolder, position: Int) {
        (holder as CheckBoxViewHolder).bind(list!![position], clickListener)
    }


    fun refreshAdapter(list: List<Checkable>) {
        this.list = list
        this.notifyDataSetChanged()
    }
    override fun getItemCount(): Int = list!!.size



    inner class CheckBoxViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.checkbox_item, parent, false)) {
        private var mCheckBox: CheckBox? = null



        init {
            mCheckBox = itemView.findViewById(R.id.checkbox)

        }



        fun bind(obj: Checkable, clickListener: (Checkable) -> Unit) {
            itemView.setOnClickListener { clickListener(obj)}
            mCheckBox?.setOnClickListener {
                obj.checked = !obj.checked
            }
            if(obj?.preference != 0)
                obj?.checked = true
            mCheckBox?.isChecked = obj?.checked
            mCheckBox?.text = obj?.designation
        }
    }




}
