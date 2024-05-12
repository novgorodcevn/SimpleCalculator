package com.codeliner.simplecalc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    private lateinit var buttonIncrease: Button
    private lateinit var counterTv: TextView
    private lateinit var buttonClear: Button
    private lateinit var buttonDecrease: Button

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.info -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        buttonIncrease = findViewById(R.id.button_increase)
        counterTv = findViewById(R.id.counter_tv)
        buttonClear = findViewById(R.id.button_clear)
        buttonDecrease = findViewById(R.id.button_decrease)

        viewModel.getCounterValueLiveData().observe(this, Observer {
            counterTv.text = it.toString()
        })
        buttonIncrease.setOnClickListener {
            viewModel.onButtonClick(R.id.button_increase)
        }
        buttonDecrease.setOnClickListener {
            viewModel.onButtonClick(R.id.button_decrease)
        }
        buttonClear.setOnClickListener {
            viewModel.onButtonClick(R.id.button_clear)
        }
    }
}
