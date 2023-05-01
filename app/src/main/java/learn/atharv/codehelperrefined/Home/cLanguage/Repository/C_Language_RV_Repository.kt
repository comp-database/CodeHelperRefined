package learn.atharv.codehelperrefined.Home.cLanguage.Repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import learn.atharv.codehelperrefined.Home.cLanguage.Model.RvItems

class C_Language_RV_Repository {
    private var databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("cProgramming")

    @Volatile private var INSTANCE : C_Language_RV_Repository? = null

    fun getInstance() : C_Language_RV_Repository {
        return INSTANCE ?: synchronized(this){
            val instance = C_Language_RV_Repository()
            INSTANCE = instance
            instance
        }
    }

    fun LoadRVTitle(rvItems : MutableLiveData<List<RvItems>>){
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try{

                    val _RvList : List<RvItems> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(RvItems::class.java)!!
                    }

                    rvItems.postValue(_RvList)
                }catch (e : Exception){
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

}