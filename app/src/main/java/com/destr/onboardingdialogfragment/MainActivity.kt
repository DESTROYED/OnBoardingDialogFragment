package com.destr.onboardingdialogfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.destr.onboardingfragment.OnboardingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initOnBoarding()
    }

    private fun initOnBoarding() {
        val onboardingFragment = OnboardingFragment()

        //Html example
        onboardingFragment.setPages(arrayListOf("https://www.google.com/?client=safari","https://yandex.by/?nr=18217"))

        //Drawable by id example
        //onboardingFragment.setImages(arrayListOf(R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground))

        //Image urls example
        //onboardingFragment.setImageUrls(arrayListOf("https://images.unsplash.com/photo-1496694048509-8e8d567dd69d?ixlib=rb-1.2.1&auto=format&fit=crop&w=3582&q=80","https://images.unsplash.com/photo-1562887284-eb863165ebc8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1700&q=80","https://images.unsplash.com/photo-1469409140200-b19fb7563ac7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=3578&q=80"))

        onboardingFragment.show(supportFragmentManager)
    }
}
