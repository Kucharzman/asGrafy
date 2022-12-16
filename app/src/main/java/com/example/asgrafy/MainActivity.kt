package com.example.asgrafy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //deklaracja grafu
        val arrGreg = arrayOf(
                   //A,B,C,D,E,F,G
            arrayOf( 0,0,0,0,0,0,0 ),//A
            arrayOf( 0,0,0,0,0,0,0 ),//B
            arrayOf( 0,0,0,0,0,0,0 ),//C
            arrayOf( 0,0,0,0,0,0,0 ),//D
            arrayOf( 0,0,0,0,0,0,0 ),//E
            arrayOf( 0,0,0,0,0,0,0 ),//F
            arrayOf( 0,0,0,0,0,0,0 ),//G
        )

        /*
        for (row in arrGreg){
            for(col in arrGreg){
                findViewById<TextView>(R.id.tvOutPut).append(arrGreg[row][col].toString())
            }
        }
         */
    }
}