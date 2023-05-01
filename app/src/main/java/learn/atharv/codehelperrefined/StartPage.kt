package learn.atharv.codehelperrefined

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import learn.atharv.codehelperrefined.databinding.ActivityStartPageBinding

class StartPage : AppCompatActivity() {
    private lateinit var binding: ActivityStartPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_start_page)

        binding = ActivityStartPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportActionBar?.hide()
//
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mentorFragment , R.id.homeFragment , R.id.discussionFragment
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}