package learn.atharv.codehelperrefined.MentorSideApp

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import learn.atharv.codehelperrefined.R

class RcAdapter(private val dataset : ArrayList<StduentDoubtModel>)
    : RecyclerView.Adapter<RcAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val ContactNo : TextView = view.findViewById(R.id.Phone)
        val Doubt : TextView = view.findViewById(R.id.Doubt)
        val Email : TextView = view.findViewById(R.id.Email)
        val Language: TextView = view.findViewById(R.id.lang)
        val Name: TextView = view.findViewById(R.id.Name)
        val Details : Button = view.findViewById(R.id.Submit)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_doubt_list,parent,false)//inflate actual item view
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item : StduentDoubtModel = dataset[position]

        holder.Name.text = item.Name.toString()
        holder.Email.text = item.Email.toString()
        holder.ContactNo.text = item.ContactNo.toString()
        holder.Doubt.text = item.Doubt.toString()
        holder.Language.text = item.Language.toString()
        val activity = holder.Details.context as Activity
        holder.Details.setOnClickListener {
            val intent = Intent(activity, DoubtDetails::class.java)
            intent.putExtra("Name" , item.Name.toString())
            intent.putExtra("Email" , item.Email.toString())
            intent.putExtra("ContactNo" , item.ContactNo.toString())
            intent.putExtra("Doubt" , item.Doubt.toString())
            intent.putExtra("Language" , item.Language.toString())
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}