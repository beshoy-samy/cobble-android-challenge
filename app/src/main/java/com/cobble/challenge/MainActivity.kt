package com.cobble.challenge

import android.os.Bundle
import android.view.LayoutInflater
import com.cobble.challenge.databinding.ActivityMainBinding
import com.cobble.core.base.BaseEmptyActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseEmptyActivity<ActivityMainBinding>() {

    override val viewBinder: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun onBindFinished(savedInstanceState: Bundle?) {
        //left to be used if needed
    }

}