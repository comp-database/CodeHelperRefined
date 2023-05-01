package learn.atharv.codehelperrefined.Mentor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import learn.atharv.codehelperrefined.R
import learn.atharv.codehelperrefined.databinding.FragmentMentorAnswerBinding

class MentorAnswer : Fragment() {
    lateinit var binding: FragmentMentorAnswerBinding
    private val db = Firebase.firestore
    private var auth: FirebaseAuth =  FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMentorAnswerBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        db.collection("User Doubts Answer").document(auth.currentUser?.email.toString()).get()
            .addOnSuccessListener{
                binding.Answer.setText(it.data?.getValue("Answer").toString())
            }
        return binding.root
    }
}