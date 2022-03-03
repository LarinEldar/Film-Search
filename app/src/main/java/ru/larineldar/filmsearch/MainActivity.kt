package ru.larineldar.filmsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createClickListener()
    }

    fun createClickListener() {
        findViewById<MaterialToolbar>(R.id.top_bar).setOnMenuItemClickListener {
            when(it.itemId){
                R.id.settings -> {
                    Toast.makeText(this, R.string.settings, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }

        }

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

}