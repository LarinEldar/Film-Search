package ru.larineldar.filmsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var timeBackPressed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createClickListener()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun createClickListener() {


        findViewById<BottomNavigationView>(R.id.bottom_bar).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.favorites -> {
                    Toast.makeText(this, R.string.favorites, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.watch_later -> {
                    Toast.makeText(this, R.string.watch_later, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.selection -> {
                    Toast.makeText(this, R.string.selection, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
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
            if (timeBackPressed + TIME_INTERVAL_BACK_PRESSED >
                System.currentTimeMillis()){

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