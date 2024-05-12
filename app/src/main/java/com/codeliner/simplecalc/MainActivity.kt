package com.codeliner.simplecalc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    
    private lateinit var resultTv: TextView
    private lateinit var formulaTv: TextView


     lateinit var layotMain: GridLayout



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
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        resultTv = findViewById(R.id.tv_result)
        formulaTv = findViewById(R.id.tv_formula)
        layotMain = findViewById(R.id.layout_main)


        viewModel.getResultValueLiveData().observe(this, Observer {
            resultTv.text = it.toString()
        })
        viewModel.getFormulaValueLiveData().observe(this, Observer{
            formulaTv.text = it.toString()
        })

        layotMain.children.filterIsInstance<Button>().forEach {button ->


            button.setOnClickListener {
                viewModel.buttont2 = button
                viewModel.onButtonClick(R.id.layout_main)

            }
        }



    }
}
