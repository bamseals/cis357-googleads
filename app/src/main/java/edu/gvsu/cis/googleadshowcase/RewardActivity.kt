package edu.gvsu.cis.googleadshowcase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class RewardActivity : AppCompatActivity() {
    private var totalReward = 0
    private var rewardedAd: RewardedAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward)
        var rewardButton = findViewById<Button>(R.id.rewardBtn)
        var rewardCount = findViewById<TextView>(R.id.rewardCount)

        loadRewardAd()

        // Interface to trigger events based on how user interacts with ad
        rewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.

                // Can set to null if you only want it to run once
                // rewardedAd = null
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                // Called when ad fails to show.
                super.onAdFailedToShowFullScreenContent(adError)
                // rewardedAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
            }
        }
        rewardButton.setOnClickListener(View.OnClickListener {
            rewardedAd?.let { ad ->
                ad.show(this, OnUserEarnedRewardListener { _ ->
                    // Handle the reward.
                    // val rewardAmount = rewardItem.amount
                    // val rewardType = rewardItem.type
                    totalReward += 1
                    rewardCount.text = "Total earned reward: $totalReward"
                    loadRewardAd()
                })
            } ?: run {
                // Ad not yet properly loaded
            }

        })
    }

    fun loadRewardAd() {
        var adRequest = AdRequest.Builder().build()
        RewardedAd.load(this,"ca-app-pub-3940256099942544/5224354917", adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                rewardedAd = null
            }

            override fun onAdLoaded(ad: RewardedAd) {
                rewardedAd = ad
            }
        })
    }
}