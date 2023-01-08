package com.example.sortowanie

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalTime::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ustawianie zmiennych
         val handler = android.os.Handler()
        var before = findViewById<TextView>(R.id.beforeTv)
        var after = findViewById<TextView>(R.id.afterTv)
        var time = findViewById<TextView>(R.id.timeTv)
        var quantiy = findViewById<EditText>(R.id.numberEd)
        var sort = findViewById<Button>(R.id.sortBtn)
        var pg = findViewById<ProgressBar>(R.id.progressBar)
        var i = 0
        after.setMovementMethod(ScrollingMovementMethod())
        before.setMovementMethod(ScrollingMovementMethod())

        //funkcja sortowania bombelkowego
        fun bubbleSort(numbers: IntArray) {

            pg.setProgress(0)
            //liczenie czasu
            val milliseconds = measureTimeMillis {
                for (pass in 0 until (numbers.size - 1)) {

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

            }


            after.text = ""
            time.text = ""
            var c = numbers.size-1
            pg.max = c
            //wypisywanie posortowanych liczb i updateoanie progres baru wraz z procesem
            Thread(Runnable{
            while (i <= c) {

                handler.post(Runnable {
                    pg.setProgress(i)
                    after.append(numbers[i].toString()+ ", ")
                })
                try {
                    Thread.sleep(20)

                }catch (e:InterruptedException){
                    e.printStackTrace()
                }
                i++

            }
                //czas nie pokazuje ile czasu zabrało wypiasnie posortowanej tabilcy ale w ile funkcja wszystko posortowała
                time.text= "sortowanie trwało: " +(milliseconds % 1000).toString() + " sekund"
            }).start()



        }




        //fukcja guzika
        sort.setOnClickListener {

                before.text = ""

                var count = quantiy.text.toString().toInt()
                //utworzenie tabvlicy losowych liczb odpowaidającej ilości wpisanej przez użytkownika
                val toSort = IntArray(count)
               for (i in 0 until count) {

                    toSort[i] = Random.nextInt(0,100)
                    before.append(toSort[i].toString() + ", ")



                    }

               bubbleSort(toSort)



        }


    }
}
/* handler.post(Runnable {
                           pg2.setProgress(i)

                       })
                       try {
                           Thread.sleep(50)

                       }catch (e:InterruptedException){
                           e.printStackTrace()
                       }*/
