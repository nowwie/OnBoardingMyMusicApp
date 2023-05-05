package com.example.budgetz.onboarding

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewParent
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.budgetz.LoginActivity
import com.example.budgetz.MainActivity
import com.example.budgetz.R
import com.google.android.material.button.MaterialButton

class OnboardingActivity : AppCompatActivity() {

    private lateinit var onboardingItemAdapter : OnBoardingItemsAdapter
    private lateinit var indicatorContainer: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setOnBoardingItems()
        setIndicator()
        setCurrentIndicator(0)
    }

    private fun setOnBoardingItems(){
        onboardingItemAdapter = OnBoardingItemsAdapter(
            listOf(
                OnBoardingItem(
                    onBoardingImage = R.drawable.fav,
                    onBoardingTitle = "Music is my therapy",
                    onBoardingDesc = "Lorem Ipsum Dolor Sit Amet"
                ),
                OnBoardingItem(
                    onBoardingImage = R.drawable.listen,
                    onBoardingTitle = "Sometimes music speaks what u feel inside",
                    onBoardingDesc = "Lorem Ipsum Dolor Sit Amet"
                ),
                OnBoardingItem(
                    onBoardingImage = R.drawable.medic,
                    onBoardingTitle = "Music is medicine",
                    onBoardingDesc = "Lorem Ipsum Dolor Sit Amet"
                ),
            )
        )
        val onBoardingViewPager = findViewById<ViewPager2>(R.id.onBoardingViewPager)
        onBoardingViewPager.adapter = onboardingItemAdapter

        onBoardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onBoardingViewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.img_right).setOnClickListener {
            if(onBoardingViewPager.currentItem +1 < onboardingItemAdapter.itemCount){
                onBoardingViewPager.currentItem += 1
            }else{
                navigateToMain()
            }
        }
        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigateToMain()
        }
        findViewById<MaterialButton>(R.id.buttonGetStarted).setOnClickListener {
            navigateToMain()
        }
    }


    private fun navigateToMain(){
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }


    private fun setIndicator(){
        indicatorContainer = findViewById(R.id.indicatorContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_bg
                    )
                )
                it.layoutParams = layoutParams
                indicatorContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if(i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_bg
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_bg
                    )
                )
            }
        }
    }
}