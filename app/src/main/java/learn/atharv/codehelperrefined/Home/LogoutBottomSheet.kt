package learn.atharv.codehelperrefined.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import learn.atharv.codehelperrefined.LoginAndSignup.LoginPage
import learn.atharv.codehelperrefined.databinding.FragmentLogoutBottomSheetBinding

class LogoutBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentLogoutBottomSheetBinding
    private val firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.UserEmail.text = firebaseAuth.currentUser?.email.toString()
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(requireContext(),LoginPage::class.java))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogoutBottomSheetBinding.inflate(inflater, container, false)

        return binding.root
    }


}