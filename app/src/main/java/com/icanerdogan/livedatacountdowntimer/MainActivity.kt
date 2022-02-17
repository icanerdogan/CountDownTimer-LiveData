package com.icanerdogan.livedatacountdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.icanerdogan.livedatacountdowntimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //viewModel.startTimer()

        viewModel.seconds.observe(this, Observer {
            binding.textViewTimer.text = it.toString()
        })

        viewModel.finished.observe(this, Observer {
            if (it == true){
                Toast.makeText(this, "Finished", Toast.LENGTH_LONG).show()
            }
            else {
                println("Devam ediyor")
            }
        })

        binding.buttonStart.setOnClickListener {

            binding.apply {
                if (editTextCount.text.isEmpty() == false){
                    val millisecond : Long = editTextCount.text.toString().toLong() * 1000
                    viewModel.millisInFuture.value = millisecond

                    viewModel.startTimer()

                }else{
                    Toast.makeText(this@MainActivity, "Invalid operation!", Toast.LENGTH_LONG).show()
                }
            }

        }

        binding.buttonStop.setOnClickListener {
            binding.apply {
                if (textViewTimer.text.equals("0")){
                    Toast.makeText(this@MainActivity, "Starting Timer!", Toast.LENGTH_LONG).show()
                }else{
                    textViewTimer.text = "0"
                    viewModel.stopTimer()
                }

            }
        }
    }
}