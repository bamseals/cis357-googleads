package edu.gvsu.cis.googleadshowcase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.*

class BannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        MobileAds.initialize(this) {}

        // Standard Banner
        val banner1 = findViewById<AdView>(R.id.banner1)
        val banner1Request = AdRequest.Builder().build()
        banner1.loadAd(banner1Request)

        // Large Banner
        val banner2 = findViewById<AdView>(R.id.banner2)
        val banner2Request = AdRequest.Builder().build()
        banner2.loadAd(banner2Request)

        // Smart Banner
        val banner3 = findViewById<AdView>(R.id.banner3)
        val banner3Request = AdRequest.Builder().build()
        banner3.loadAd(banner3Request)




    }
}