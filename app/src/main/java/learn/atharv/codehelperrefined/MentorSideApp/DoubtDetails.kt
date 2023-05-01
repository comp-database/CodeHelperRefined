package learn.atharv.codehelperrefined.MentorSideApp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import learn.atharv.codehelperrefined.R
import learn.atharv.codehelperrefined.databinding.ActivityDoubtDetailsBinding

class DoubtDetails : AppCompatActivity() {
    private val db = Firebase.firestore
    lateinit var binding : ActivityDoubtDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoubtDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_doubt_details)
        val Name = intent.getStringExtra("Name").toString()
        val Email = intent.getStringExtra("Email").toString()
        val Language = intent.getStringExtra("Language").toString()
        val Doubt = intent.getStringExtra("Doubt").toString()
        val ContactNo = intent.getStringExtra("ContactNo").toString()


        binding.Name.setText(Name)
        binding.Email.setText(Email)
//        binding.Language.setText(Language)
        binding.Doubt.setText(Doubt)
//        binding.ContactNo.setText(ContactNo)

        binding.sendAnswer.setOnClickListener {
            val Answer = binding.Answer.text.toString()
            val UserDoubtInfo = hashMapOf(
                "Answer" to Answer
            )
            db.collection("User Doubts Answer").document(Email).set(UserDoubtInfo).addOnSuccessListener {
                Toast.makeText(this, "Your response Stored Successfully", Toast.LENGTH_SHORT).show()
                binding.Answer.text.clear()
//                db.collection()
            }
        }
        binding.callStudent.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            val phone : String = ContactNo.toString()
            dialIntent.data = Uri.parse("tel:$phone")
            startActivity(dialIntent)
        }
    }
}