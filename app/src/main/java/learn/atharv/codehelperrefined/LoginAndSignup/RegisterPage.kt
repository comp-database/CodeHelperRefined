package learn.atharv.codehelperrefined.LoginAndSignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import learn.atharv.codehelperrefined.StartPage
import learn.atharv.codehelperrefined.databinding.ActivityRegisterPageBinding

class RegisterPage : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterPageBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        firebaseAuth = FirebaseAuth.getInstance()
        val db = Firebase.firestore
//        binding.olduser.setOnClickListener {
//            val intent = Intent(this, MainActivity2::class.java)
//            startActivity(intent)
//        }
        binding.Register.setOnClickListener {
            val name = binding.name.text.toString()
            val email = binding.email.text.toString()
            val pass = binding.pass1.text.toString()
            val confirmPass = binding.pass2.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty() && (email != firebaseAuth.currentUser?.email)) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, StartPage::class.java)
                            startActivity(intent)
                            val UserInfo = hashMapOf(
                                "Name" to name,
                                "Email" to email
                            )
                            db.collection("User Information").document(firebaseAuth.currentUser?.email.toString()).set(UserInfo).addOnSuccessListener {
                                Toast.makeText(this, "Your response Stored Successfully", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}