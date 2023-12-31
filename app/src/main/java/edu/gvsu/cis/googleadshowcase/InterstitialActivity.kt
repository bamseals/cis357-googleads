package edu.gvsu.cis.googleadshowcase

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class InterstitialActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null
    private val TAG = "InterstitialActivity"
    private var countdown = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial)

        MobileAds.initialize(this) {}

        var adRequest = AdRequest.Builder().build()
        var timeLabel = findViewById<TextView>(R.id.ad_countdown)

        // begin countdown timer
        countDown(timeLabel)

        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.toString())
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd

                    // Set the FullScreenContentCallback
                    mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                        override fun onAdClicked() {
                            Log.d(TAG, "Ad was clicked.")
                        }

                        override fun onAdDismissedFullScreenContent() {
                            Log.d(TAG, "Ad dismissed fullscreen content.")
                            mInterstitialAd = null
                            // You may want to finish the activity or perform any other action here.
                            finish()
                        }

                        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                            // Called when ad fails to show.
                            Log.e(TAG, "Ad failed to show fullscreen content.")
                            mInterstitialAd = null
                        }

                        override fun onAdImpression() {
                            Log.d(TAG, "Ad recorded an impression.")
                        }

                        override fun onAdShowedFullScreenContent() {
                            Log.d(TAG, "Ad showed fullscreen content.")
                        }
                    }
                    // showInterstitialAd()
                }
            })
    }

    // Call this method to show the interstitial ad
    private fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {
            Log.d(TAG, "The interstitial ad wasn't ready yet.")
        }
    }

    private fun countDown(label: TextView) {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (countdown > 0) {
                    countdown -= 1
                    label.text = "Ad will run in ${countdown}..."
                    countDown(label)
                } else {
                    Log.d(TAG, "showAd:")
                    showInterstitialAd()
                }
            },
            1000 // value in milliseconds
        )
    }
}
