package learn.atharv.codehelperrefined.MentorSideApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import learn.atharv.codehelperrefined.R
import learn.atharv.codehelperrefined.databinding.ActivityMentorSideStartPageBinding

class MentorSideStartPage : AppCompatActivity() {
    lateinit var binding : ActivityMentorSideStartPageBinding
    var db = Firebase.firestore
    lateinit var datalist: ArrayList<StduentDoubtModel>
    lateinit var adapterRc :RcAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMentorSideStartPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_mentor_side_start_page)
        val MentorName = intent.getStringExtra("MentorName").toString()
        binding.MentorName.text = MentorName


        val recyclerView = binding.recycleview
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        datalist = arrayListOf()
        adapterRc = RcAdapter(datalist)
        recyclerView.adapter = adapterRc


        EventChangeListener(MentorName)

    }
    private fun EventChangeListener(MentorName : String) {
        db = FirebaseFirestore.getInstance()
        db.collection("User Doubts $MentorName")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value : QuerySnapshot?,
                    error : FirebaseFirestoreException?
                ){
                    if (error != null){
                        Log.e("Firestore Error",error.message.toString())
                        return
                    }
                    for(dc : DocumentChange in value?.documentChanges!!){
                        if(dc.type == DocumentChange.Type.ADDED){
                            datalist.add(dc.document.toObject(StduentDoubtModel::class.java))
                        }
                    }
                    adapterRc.notifyDataSetChanged()
                }
            })
    }
}