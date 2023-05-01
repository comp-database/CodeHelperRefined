package learn.atharv.codehelperrefined.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import learn.atharv.codehelperrefined.Home.cLanguage.C_Language_Host
import learn.atharv.codehelperrefined.R
import learn.atharv.codehelperrefined.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var auth: FirebaseAuth =  FirebaseAuth.getInstance()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        db.collection("User Information").document(auth.currentUser?.email.toString()).get()
            .addOnSuccessListener{
                var UserName = it.data?.getValue("Name").toString()
                if (UserName == "null"){
                    UserName = auth.currentUser?.displayName.toString()
                }
                binding.UserEmail.text = UserName
                Log.d("firebase Data","got data is ${it.data?.values.toString() }")
            }
//        binding.UserEmail.text = auth.currentUser?.email.toString()
        binding.CLanguage.setOnClickListener {
//            startActivity(Intent(requireContext(),C_Language_Host::class.java))
            it.findNavController().navigate(R.id.action_homeFragment_to_c_Language_Host)
        }
        binding.Profile.setOnClickListener {
            LogoutBottomSheet().show(parentFragmentManager,"TAG")
        }
        return root

    }
}