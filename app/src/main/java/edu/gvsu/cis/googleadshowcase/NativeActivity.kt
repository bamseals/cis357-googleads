package edu.gvsu.cis.googleadshowcase

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView

class NativeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native)

        val adLoader = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
            .forNativeAd { ad: NativeAd ->
                // Show the ad.
                Log.d("NativeAd", "Ad loaded: ${ad.headline}")

                // Call the displayNativeAd function to display the ad in the adContainer.
                displayNativeAd(findViewById(R.id.adContainer), ad)
            }
            // Rest of AdLoader configuration
            .build()

        val adRequest = AdRequest.Builder().build()
        adLoader.loadAd(adRequest)
    }

    private fun displayNativeAd(parent: ViewGroup, ad: NativeAd) {
        // Inflate the native_ad_layout.xml and add it to the parent ViewGroup.
        val inflater = LayoutInflater.from(parent.context)
        val adView = inflater.inflate(R.layout.native_ad_layout, parent, false) as NativeAdView

        // Locate the view that will hold the headline, set its text, and use the
        // NativeAdView's headlineView property to register it.
        val headlineView = adView.findViewById<TextView>(R.id.ad_headline)
        headlineView.text = ad.headline
        adView.headlineView = headlineView

        // Locate the MediaView and set it up if media content is available.
        val mediaView = adView.findViewById<MediaView>(R.id.ad_media)
        if (ad.mediaContent != null) {
            mediaView.setMediaContent(ad.mediaContent)
        }

        // Call the NativeAdView's setNativeAd method to register the NativeAdObject.
        adView.setNativeAd(ad)

        // Make sure parent view doesn't already contain an ad view.
        parent.removeAllViews()

        // Place the AdView into parent.
        parent.addView(adView)
    }
}
