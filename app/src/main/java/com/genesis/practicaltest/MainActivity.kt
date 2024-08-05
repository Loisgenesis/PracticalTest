package com.genesis.practicaltest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.genesis.practicaltest.viewmodel.CategoryViewModel
import com.genesis.practicaltest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.categories.observe(this) {category->
            binding.recyclerView.adapter = CategoryAdapter(category)
        }
        binding.btnRetry.setOnClickListener {
            viewModel.fetchCategories()
        }

    }
}