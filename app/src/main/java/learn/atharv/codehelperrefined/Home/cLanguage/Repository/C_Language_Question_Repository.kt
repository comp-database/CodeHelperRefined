package learn.atharv.codehelperrefined.Home.cLanguage.Repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import learn.atharv.codehelperrefined.Home.cLanguage.Model.Question
import learn.atharv.codehelperrefined.Home.cLanguage.Model.RvItems

class C_Language_Question_Repository {
    private var databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Question")

    @Volatile private var INSTANCE : C_Language_Question_Repository? = null

    fun getInstance() : C_Language_Question_Repository {
        return INSTANCE ?: synchronized(this){
            val instance = C_Language_Question_Repository()
            INSTANCE = instance
            instance
        }
    }

    fun LoadQuestion(Questions : MutableLiveData<List<Question>>){
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try{

                    val _QuestionList : List<Question> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Question::class.java)!!
                    }

                    Questions.postValue(_QuestionList)
                }catch (e : Exception){
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}