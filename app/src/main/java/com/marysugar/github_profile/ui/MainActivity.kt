package com.marysugar.github_profile.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.marysugar.github_profile.R
import com.marysugar.github_profile.databinding.ActivityMainBinding
import com.marysugar.github_profile.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setFragment(ProfileFragment(), ProfileFragment.TAG)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.profile -> {
                    setFragment(ProfileFragment(), ProfileFragment.TAG)
                    binding.toolbar.title = mainViewModel.toolbarTitleProfile
                }
                R.id.repository -> {
                    setFragment(RepositoryListFragment(), RepositoryListFragment.TAG)
                    binding.toolbar.title = mainViewModel.toolbarTitleRepository
                }
            }
            true
        }
    }
    private fun setFragment(fragment: Fragment, tag: String) {
        val currentFragment = supportFragmentManager.findFragmentByTag(tag)
        // 既に同じフラグメントが表示されている場合replaceしない
        if (currentFragment != null && currentFragment.isVisible) {
            Log.d(TAG, "Same fragment already")
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, fragment, tag)
                commit()
            }
        }
    }

    fun setContentFragment(fragment: Fragment, tag: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(tag)
            replace(R.id.container, fragment, tag)
            commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportFragmentManager.popBackStack()
        return true
    }

    companion object {
        const val TAG = "MainActivity"
    }
}