package learn.atharv.codehelperrefined.Home.cLanguage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import learn.atharv.codehelperrefined.R
import learn.atharv.codehelperrefined.databinding.ActivityClanguageHostBinding
import learn.atharv.codehelperrefined.databinding.ActivityStartPageBinding

class C_Language_Host : AppCompatActivity() {
    private lateinit var binding: ActivityClanguageHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClanguageHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}