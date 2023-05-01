package learn.atharv.codehelperrefined.Mentor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import learn.atharv.codehelperrefined.R

class MentorHost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentor_host)
        val MentorNum = intent?.getStringExtra("MentorNum").toString()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val myFragment = MentorForm()
        val bundle = Bundle()
        bundle.putString("MentorNum", MentorNum)
        myFragment.arguments = bundle
        fragmentTransaction.add(R.id.fragmentContainerView, myFragment).commit()
    }
}