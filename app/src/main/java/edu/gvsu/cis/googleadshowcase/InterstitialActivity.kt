package edu.gvsu.cis.googleadshowcase

import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial)

        MobileAds.initialize(this) {}

        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",  // Use your actual ad unit ID
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
                    showInterstitialAd()
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
}
