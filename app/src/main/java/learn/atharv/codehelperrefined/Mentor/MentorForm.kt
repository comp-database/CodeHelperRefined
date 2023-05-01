package learn.atharv.codehelperrefined.Mentor

import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CheckBox
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import learn.atharv.codehelperrefined.Home.cLanguage.C_Language_DetailsArgs
import learn.atharv.codehelperrefined.R
import learn.atharv.codehelperrefined.databinding.FragmentMentorFormBinding

class MentorForm : Fragment() {
    private lateinit var binding: FragmentMentorFormBinding
    private val db = Firebase.firestore
    private var auth: FirebaseAuth =  FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorFormBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        db.collection("User Information").document(auth.currentUser?.email.toString()).get()
            .addOnSuccessListener{
                binding.UserName.setText(it.data?.getValue("Name").toString())
                binding.UserEmail.setText(it.data?.getValue("Email").toString())
            }

        val bundle = arguments
        val MentorNum = bundle?.getString("MentorNum")
        var mentorNumber = MentorNum
        Log.d("MentorNum", "${mentorNumber}" )
//        val MentorNum = args
        binding.UserPhone.visibility = View.INVISIBLE
        binding.PhoneCallCheck
            .setOnCheckedChangeListener { buttonView, isChecked  ->
            if (isChecked){
                Log.d("CHECKBOXES", "Checked")
                binding.UserPhone.visibility = View.VISIBLE
//                dialog()
            }else{
                Log.d("CHECKBOXES", "Not Checked")
                binding.UserPhone.visibility = View.INVISIBLE
            }
        }

        binding.BtnSubmit.setOnClickListener {
            val name = binding.UserName.text.toString().trim()
            val email = binding.UserEmail.text.toString().trim()
            val language = binding.UserLanguage.text.toString().trim()
            val doubt = binding.UserDoubt.text.toString().trim()
            val contact = binding.UserPhone.text.toString().trim()
            val UserDoubtInfo = hashMapOf(
                "Name" to name,
                "Email" to email,
                "Language" to language,
                "Doubt" to doubt,
                "ContactNo" to contact,
            )
            db.collection("User Doubts $mentorNumber").document("${auth.currentUser?.email}").set(UserDoubtInfo).addOnSuccessListener {
                Toast.makeText(requireContext(), "Your response Stored Successfully", Toast.LENGTH_SHORT).show()
                binding.UserDoubt.text?.clear()
                binding.UserLanguage.text?.clear()
                binding.UserPhone.text?.clear()
            }
        }
        return binding.root
    }
}