package ru.larineldar.filmsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

const val HOME_FRAGMENT_TAG = "home"
const val FAVORITE_FRAGMENT_TAG = "favorite"
const val WATCH_LATER_FRAGMENT_TAG = "watch_later"
const val SELECTION_FRAGMENT_TAG = "selection"

class MainActivity : AppCompatActivity() {
    var timeBackPressed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createClickListener()

        startFragment(HomeFragment(), HOME_FRAGMENT_TAG)
    }

    private fun createClickListener() {
        findViewById<BottomNavigationView>(R.id.bottom_bar).setOnNavigationItemSelectedListener {
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
        bundle.putParcelable("film", film)
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
        const val TIME_INTERVAL_BACK_PRESSED = 2000
    }
}