package com.test.project

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.test.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val tabTitles = listOf("Applications", "Settings")
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backBtn.setOnClickListener {
            finishAffinity()
        }

        viewPagerAdapter = ViewPagerAdapter(this)

        viewPagerAdapter.addFragment(AppListFragment(), "Applications")
        viewPagerAdapter.addFragment(SettingsFragment(), "Settings")

        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = viewPagerAdapter.getPageTitle(position)
        }.attach()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}