package com.destr.onboardingfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.onboarding_item.view.*

class OnboardingDrawableAdapter(private val items: ArrayList<*>, private val isHtml: Boolean) :
    RecyclerView.Adapter<OnboardingDrawableAdapter.OnBoardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OnBoardingViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.onboarding_item,
            parent,
            false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        if (isHtml) {
            holder.onBoardingWebView.visibility = View.VISIBLE
            holder.onBoardingWebView.loadUrl(items[position].toString())
        } else {
            holder.onboardingImage.visibility = View.VISIBLE
            Glide.with(holder.onboardingImage.context).load(items[position])
                .into(holder.onboardingImage)
        }
    }

    class OnBoardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val onboardingImage: ImageView = itemView.onboardingImage
        val onBoardingWebView: WebView = itemView.onboardingWebView
    }
}
