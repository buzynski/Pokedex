package com.buzynski.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.buzynski.pokedex.databinding.ActivitySplashScreenBinding

private const val SPLASHSCREEN_TIMEOUT = 5000L

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initProgressBar()
        setTimer()
    }

    private fun initProgressBar() {
        binding.progressBar.setBarMaximum(SPLASHSCREEN_TIMEOUT.toInt())
    }

    private fun setTimer() {
        val countDownTimer = object : CountDownTimer(SPLASHSCREEN_TIMEOUT, 1000) {
            override fun onTick(p0: Long) {
                binding.progressBar.setProgress((SPLASHSCREEN_TIMEOUT - p0).toInt())
            }

            override fun onFinish() {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            }
        }

        countDownTimer.start()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    override fun onBackPressed() { return }
}