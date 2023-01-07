package com.example.sortowanie

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Handler
import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalTime::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var before = findViewById<TextView>(R.id.beforeTv)
        var after = findViewById<TextView>(R.id.afterTv)
        var time = findViewById<TextView>(R.id.timeTv)
        var quantiy = findViewById<EditText>(R.id.numberEd)
        var sort = findViewById<Button>(R.id.sortBtn)
        var pg = findViewById<ProgressBar>(R.id.progressBar)
        var pg2 = findViewById<ProgressBar>(R.id.progressBar2)
        var i = 0


        fun bubbleSort(numbers: IntArray) {
            pg.max = numbers.size
            pg.setProgress(0)
            val milliseconds = measureTime {
            for (pass in 0 until (numbers.size - 1)) {
                pg.setProgress(pass)
                // A single pass of bubble sort
                for (currentPosition in 0 until (numbers.size - 1)) {
                    // This is a single step
                    if (numbers[currentPosition] > numbers[currentPosition + 1]) {
                        val tmp = numbers[currentPosition]
                        numbers[currentPosition] = numbers[currentPosition + 1]
                        numbers[currentPosition + 1] = tmp
                    }

                }
            }
            after.text = ""
            var c = numbers.size
            for (i in 0 until c) {
                after.append(numbers[i].toString()+ " ")
            }

        }
            time.text= milliseconds.toString()
        }

        sort.setOnClickListener {
            before.text = ""
            pg2.setProgress(0)

            var count = quantiy.text.toString().toInt()
            pg2.max = count
            val toSort = IntArray(count)


            while (i <count) {

                toSort[i] = Random.nextInt(0,100)

                pg2.setProgress(i)
                before.append(toSort[i].toString() + " ")
                i++
            }
            bubbleSort(toSort)


        }

    }
}