package com.destr.onboardingfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.*
import kotlinx.android.synthetic.main.onboarding_view.*

class OnboardingFragment : DialogFragment() {

    companion object {
        private const val NON_HTML_TYPE = 0
        private const val HTML_TYPE = 1
    }

    private var adapterType = 666
    private var images: ArrayList<*>? = null
    private var pages: ArrayList<*>? = null
    private var isHtml = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.onboarding_view, container, true)
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }

    fun setImages(images: ArrayList<*>) {
        adapterType = NON_HTML_TYPE
        this.images = images
    }

    fun setPages(pages: ArrayList<String>) {
        adapterType = HTML_TYPE
        this.pages = pages
        isHtml = true
    }

    fun show(supportFragmentManager: FragmentManager) {
        this.show(supportFragmentManager, this::javaClass.name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = when (adapterType) {
            NON_HTML_TYPE -> {
                images?.let { OnboardingDrawableAdapter(it, isHtml) }
            }
            HTML_TYPE -> {
                pages?.let { OnboardingDrawableAdapter(it, isHtml) }
            }
            else -> {
                throw OnboardingHasNoItemsException()
            }
        }

        initViewpager(adapter)
    }

    private fun initViewpager(adapter: OnboardingDrawableAdapter?) {
        adapter?.let {

            viewPager.adapter = adapter

            TabLayoutMediator(
                tabLayout,
                viewPager,
                TabLayoutMediator.TabConfigurationStrategy
                { tab, _ ->
                    tab.select()
                }).attach()

            boardingButton.setOnClickListener {
                if (boardingButton.text == getString(R.string.notLastButtonText)) {
                    viewPager.currentItem += 1
                } else {
                    this.dismiss()
                }
            }

            viewPager.registerOnPageChangeCallback(
                object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        if (position < adapter.itemCount - 1) {
                            boardingButton.text = getString(R.string.notLastButtonText)
                        } else {
                            boardingButton.text = getString(R.string.lastButtonText)
                        }
                        super.onPageSelected(position)
                    }
                })
        }
    }
}