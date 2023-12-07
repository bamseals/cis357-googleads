package edu.gvsu.cis.googleadshowcase

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView

class NativeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native)

        val adLoader = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
            .forNativeAd { ad: NativeAd ->
                // Show the ad.
                Log.d("NativeAd", "Ad loaded: ${ad.headline}")

                val adView = findViewById<NativeAdView>(R.id.adView)

                // Check if media content is available
                if (ad.mediaContent != null) {
                    val mediaView = adView.findViewById<MediaView>(R.id.ad_media)
                    mediaView.setMediaContent(ad.mediaContent)
                    Log.d("NativeAd", "Media content is not null.")
                } else {
                    Log.e("NativeAd", "Media content is null.")
                }

                val headlineView = adView.findViewById<TextView>(R.id.ad_headline)
                headlineView.text = ad.headline
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    // Handle the failure by logging
                    Log.e("NativeAd", "Ad failed to load: ${adError.message}")
                }
            })
            .withNativeAdOptions(NativeAdOptions.Builder().build())
            .build()

        val adRequest = AdRequest.Builder().build()
        adLoader.loadAd(adRequest)

    }
}