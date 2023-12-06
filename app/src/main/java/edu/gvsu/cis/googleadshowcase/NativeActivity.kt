package edu.gvsu.cis.googleadshowcase
/*
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView


class NativeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native)

        loadNativeAd()
    }

    private fun loadNativeAd() {
        val adLoader = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
            .forNativeAd { nativeAd ->

                val adView = LayoutInflater.from(this)
                    .inflate(R.layout.activity_native, null) as NativeAdView


                populateNativeAdView(nativeAd, adView)


                val adContainer = findViewById<FrameLayout>(R.id.nativeAdContainer)
                adContainer.removeAllViews()
                adContainer.addView(adView)
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(adError: LoadAdError) {

                }
            })
            .withNativeAdOptions(NativeAdOptions.Builder().build())
            .build()

        adLoader.loadAd(AdRequest.Builder().build())
    }


    private fun populateNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {

        val appIconView = adView.findViewById<ImageView>(R.id.ad_app_icon)
        val headlineView = adView.findViewById<TextView>(R.id.ad_headline)


        appIconView.setImageDrawable(nativeAd.icon?.drawable)
        headlineView.text = nativeAd.headline


        adView.iconView = appIconView
        adView.headlineView = headlineView


    }
}
*/