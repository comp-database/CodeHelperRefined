package learn.atharv.codehelperrefined.Mentor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import learn.atharv.codehelperrefined.Home.LogoutBottomSheet
import learn.atharv.codehelperrefined.Home.cLanguage.C_Language_LearningDirections
import learn.atharv.codehelperrefined.R
import learn.atharv.codehelperrefined.databinding.FragmentMentorBinding

class MentorFragment : Fragment() {
    private lateinit var binding: FragmentMentorBinding
    private val db = Firebase.firestore
    private var auth: FirebaseAuth =  FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentMentorBinding.inflate(inflater, container, false)


        db.collection("User Information").document(auth.currentUser?.email.toString()).get()
            .addOnSuccessListener{
                var UserName = it.data?.getValue("Name").toString()
                if (UserName == "null"){
                    UserName = auth.currentUser?.displayName.toString()
                }
                binding.userName.text = UserName
                Log.d("firebase Data","got data is ${it.data?.values.toString() }")
            }

        binding.MentorOneCard.setOnClickListener {
            val intent = Intent(requireContext(),MentorHost::class.java)
            intent.putExtra("MentorNum","One")
            startActivity(intent)
//            it.findNavController().navigate(R.id.action_mentorFragment_to_mentorHost)
//            MentorForm().(parentFragmentManager,"TAG")
        }
        binding.MentorTwoCard.setOnClickListener {
            val intent = Intent(requireContext(),MentorHost::class.java)
            intent.putExtra("MentorNum","Two")
            startActivity(intent)
//            it.findNavController().navigate(R.id.action_mentorFragment_to_mentorHost)
        }
        binding.MentorThreeCard.setOnClickListener {
            val intent = Intent(requireContext(),MentorHost::class.java)
            intent.putExtra("MentorNum","Three")
            startActivity(intent)
//            it.findNavController().navigate(R.id.action_mentorFragment_to_mentorHost)
        }
        binding.Answer.setOnClickListener {
            it.findNavController().navigate(R.id.action_mentorFragment_to_mentorAnswer)
        }
        return binding.root
    }


}