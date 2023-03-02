package com.eypancakir.oyundenemesiv1

import android.os.Bundle
import com.eypancakir.oyundenemesiv1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val boardFragment = BoardFragment()
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, boardFragment)
            .commit()
    }
}

