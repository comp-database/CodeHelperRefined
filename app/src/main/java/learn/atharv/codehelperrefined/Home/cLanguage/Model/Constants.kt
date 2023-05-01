package learn.atharv.codehelperrefined.Home.cLanguage.Model

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
object Constants {
    val dbref = FirebaseDatabase.getInstance().getReference("Question")
    fun getQuetions() : ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val question = userSnapshot.getValue(Question::class.java)
                        questionsList.add(question!!)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return questionsList
    }
}
