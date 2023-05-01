package learn.atharv.codehelperrefined.Home.cLanguage.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import learn.atharv.codehelperrefined.Home.cLanguage.C_Language_LearningDirections
import learn.atharv.codehelperrefined.Home.cLanguage.Model.RvItems
import learn.atharv.codehelperrefined.databinding.FragmentCLanguageLearningBinding

class C_Language_Learning_Adapter() : RecyclerView.Adapter<C_Language_Learning_Adapter.ViewHolder>() {
    private var RV_DATAs = ArrayList<RvItems>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentCLanguageLearningBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = RV_DATAs[position]
        holder.TopicName.text = item.title.toString()
        holder.BoxClick.setOnClickListener {
            val Direction = C_Language_LearningDirections.actionCLanguageLearningToCLanguageDetails(
                item.title.toString(),
                item.data.toString()
            )
            it.findNavController().navigate(Direction)
        }
    }

    override fun getItemCount(): Int = RV_DATAs.size


    inner class ViewHolder(binding: FragmentCLanguageLearningBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val TopicName: TextView = binding.TopicName
        val BoxClick : CardView = binding.DetailsClick
    }
    fun updateRvItems(orderList: List<RvItems>) {
        this.RV_DATAs.clear()
        this.RV_DATAs.addAll(orderList)
        notifyDataSetChanged()
    }

}