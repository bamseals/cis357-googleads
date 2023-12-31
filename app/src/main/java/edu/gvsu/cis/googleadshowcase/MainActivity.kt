package edu.gvsu.cis.googleadshowcase


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this) {}

        var bannerBtn = findViewById<Button>(R.id.nav_banners)
        bannerBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, BannerActivity::class.java)
            startActivity(intent)
        })

        var interstitialButton = findViewById<Button>(R.id.nav_interstitial)
        interstitialButton.setOnClickListener(View.OnClickListener {
            val intent2 = Intent(this@MainActivity, InterstitialActivity::class.java)
            startActivity(intent2)
        })

        var rewardButton = findViewById<Button>(R.id.nav_reward)
        rewardButton.setOnClickListener(View.OnClickListener {
            val intent3 = Intent(this@MainActivity, RewardActivity::class.java)
            startActivity(intent3)
        })

        var nativeButton = findViewById<Button>(R.id.nav_native)
        nativeButton.setOnClickListener(View.OnClickListener {
            val intent4 = Intent(this@MainActivity, NativeActivity::class.java)
            startActivity(intent4)
        })
    }
}

