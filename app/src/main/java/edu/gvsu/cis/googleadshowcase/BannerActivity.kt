package edu.gvsu.cis.googleadshowcase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class BannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        MobileAds.initialize(this) {}

        val banner1 = findViewById<AdView>(R.id.banner1)
        val banner1Request = AdRequest.Builder().build()
        banner1.loadAd(banner1Request)
    }
}