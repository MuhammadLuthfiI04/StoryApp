package com.bangkit.storyapp.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.bangkit.storyapp.R
import com.bangkit.storyapp.databinding.ActivityDetailBinding
import com.bangkit.storyapp.model.StoryModel
import com.bangkit.storyapp.ui.login.LoginActivity
import com.bangkit.storyapp.ui.main.MainViewModel
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val story = intent.getParcelableExtra<StoryModel>(DETAIL_STORY) as StoryModel
        Glide.with(this)
            .load(story.photo)
            .error(R.drawable.ic_broken_image)
            .placeholder(R.drawable.anim_progress_icon)
            .into(binding.imgPhoto)
        binding.tvName.text = story.name
        binding.tvDescription.text = story.description
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_language -> {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
                return true
            }

            R.id.menu_logout -> {
                mainViewModel.logout()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return true
    }

    companion object {
        const val DETAIL_STORY = "detail_story"
    }
}