package learn.atharv.codehelperrefined

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import learn.atharv.codehelperrefined.LoginAndSignup.LoginPage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this@MainActivity , LoginPage::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}