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
import kotlin.properties.Delegates

class OnboardingFragment : DialogFragment() {

    companion object {
        private const val NON_EMPTY_TYPE = 0
    }

    private var adapterType: Int? = null
    private var pages: ArrayList<*>? = null
    private var isHtml by Delegates.notNull<Boolean>()

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

    /**
     * Method, to set images into adapter.
     * @param pages could be used like setter for elements,
     * like Html links, that will be launched at onboarding page
     */
    fun setPages(pages: ArrayList<*>, isHtml: Boolean = false) {
        adapterType = NON_EMPTY_TYPE
        this.pages = pages
        this.isHtml = isHtml
    }

    /**
     * Method, to open DialogFragment with internal TAG,
     */
    fun show(supportFragmentManager: FragmentManager) {
        this.show(supportFragmentManager, this::javaClass.name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = getAdapterByType()
        initViewpager(adapter)
    }

    private fun initViewpager(adapter: OnboardingDrawableAdapter?) {
        adapter?.let { notNullAdapter ->

            viewPager.adapter = notNullAdapter

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
                        boardingButton.text = getOnboardingButtonText(position, notNullAdapter)
                        super.onPageSelected(position)
                    }
                })
        }
    }

    private fun getOnboardingButtonText(
        position: Int,
        adapter: OnboardingDrawableAdapter
    ): String? {
        return if (position < adapter.itemCount - 1) {
            getString(R.string.notLastButtonText)
        } else {
            getString(R.string.lastButtonText)
        }
    }

    private fun getAdapterByType(): OnboardingDrawableAdapter? {
        return when (adapterType) {
            NON_EMPTY_TYPE -> {
                pages?.let { OnboardingDrawableAdapter(it, isHtml) }
            }
            else -> {
                throw OnboardingHasNoItemsException()
            }
        }
    }
}