package pl.brolek.starwarsapp.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import pl.brolek.starwarsapp.main.MainActivity
import pl.brolek.starwarsapp.R

class SplashActivity : AppCompatActivity() {

    companion object {
        private val SPLASH_SCREEN_DURATION = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        scheduleSplashScreen()
    }

    private fun scheduleSplashScreen() {
        Handler().postDelayed({ startMainActivity() }, SPLASH_SCREEN_DURATION)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
