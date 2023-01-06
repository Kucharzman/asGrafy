package com.example.asgrafy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //deklaracja grafu
        val arrGreg = arrayOf(
                   //A,B,C,D,E,F
            arrayOf( 0,1,0,0,0,0 ),//A
            arrayOf( 0,0,0,0,0,0 ),//B
            arrayOf( 0,0,0,0,0,0 ),//C
            arrayOf( 0,0,0,1,0,0 ),//D
            arrayOf( 0,0,0,0,0,1 ),//E
            arrayOf( 0,0,0,0,0,0 ) //F
        )


        //wyswietlanie grafu
        findViewById<Button>(R.id.bttGraph).setOnClickListener {

            findViewById<TextView>(R.id.tvOutPut).text = ""
            var cTx = ""
            var rTx = ""

            for (r in 0..5){
                for (c in 0..5){
                    if (arrGreg[r][c] == 1){
                        when(r){
                            0 -> cTx = "A"
                            1 -> cTx = "B"
                            2 -> cTx = "C"
                            3 -> cTx = "D"
                            4 -> cTx = "E"
                            5 -> cTx = "F"
                        }
                        when(c){
                            0 -> rTx = "A"
                            1 -> rTx = "B"
                            2 -> rTx = "C"
                            3 -> rTx = "D"
                            4 -> rTx = "E"
                            5 -> rTx = "F"
                        }

                        findViewById<TextView>(R.id.tvOutPut).append("Połączenie " + rTx + " z " + cTx +"\n")
                    }
                }
            }
        }

        /*
        for (row in arrGreg){
            for(col in arrGreg){
                findViewById<TextView>(R.id.tvOutPut).append(arrGreg[row][col].toString())
            }
        }
         */
    }
}