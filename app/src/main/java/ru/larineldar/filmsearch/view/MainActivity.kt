package ru.larineldar.filmsearch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.larineldar.filmsearch.R
import ru.larineldar.filmsearch.databinding.ActivityMainBinding
import ru.larineldar.filmsearch.data.entity.Film
import ru.larineldar.filmsearch.view.fragments.*

class MainActivity : AppCompatActivity() {
    var timeBackPressed = 0L
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomBar()

        startFragment(HomeFragment(), HOME_FRAGMENT_TAG)
    }

    private fun initBottomBar() {
        binding.bottomBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    startFragment(HomeFragment(), HOME_FRAGMENT_TAG)
                    true
                }

                R.id.favorites -> {
                    startFragment(FavoritesFragment(), FAVORITE_FRAGMENT_TAG)
                    true
                }

                R.id.watch_later -> {
                    startFragment(WatchLaterFragment(), WATCH_LATER_FRAGMENT_TAG)
                    true
                }

                R.id.selection -> {
                    startFragment(SelectionFragment(), SELECTION_FRAGMENT_TAG)
                    true
                }

                R.id.settings -> {
                    startFragment(SettingsFragment(), SETTINGS_FRAGMENT_TAG)
                    true
                }

                else -> false
            }
        }
    }

    private fun startFragment(defaultFragment: Fragment, tag: String){
        val fragment = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment ?: defaultFragment, tag)
            .addToBackStack(null)
            .commit()
    }

    fun launchDetailsFragment(film : Film){
        val bundle = Bundle()
        bundle.putParcelable(DetailsFragment.BUNDLE_FILM_TAG, film)
        val fragment = DetailsFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1){
            if (timeBackPressed + TIME_INTERVAL_BACK_PRESSED > System.currentTimeMillis()){
                super.onBackPressed()
                finish()
            } else {
                Toast.makeText(this, R.string.back_pressed, Toast.LENGTH_SHORT).show()
            }

            timeBackPressed = System.currentTimeMillis()

        } else {
            super.onBackPressed()
        }
    }

    companion object{
        const val HOME_FRAGMENT_TAG = "home"
        const val FAVORITE_FRAGMENT_TAG = "favorite"
        const val WATCH_LATER_FRAGMENT_TAG = "watch_later"
        const val SELECTION_FRAGMENT_TAG = "selection"
        const val SETTINGS_FRAGMENT_TAG = "settings"
        const val TIME_INTERVAL_BACK_PRESSED = 2000
    }
}