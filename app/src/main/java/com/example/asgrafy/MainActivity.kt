package com.example.asgrafy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var edtCol : EditText
    lateinit var edtRow : EditText
    lateinit var bttConn: Button
    lateinit var bttDijk: Button
    lateinit var bttShow: Button
    lateinit var tvOutPut: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtCol = findViewById(R.id.edtWezelKol)
        edtRow = findViewById(R.id.edtWezelWier)
        bttConn= findViewById(R.id.bttPolaczenieZat)
        bttDijk= findViewById(R.id.bttDijkstra)
        bttShow= findViewById(R.id.bttGraph)
        tvOutPut=findViewById(R.id.tvOutPut)

        //deklaracja grafu
        var arrGreg = arrayOf(
                   //A,B,C,D,E,F
            arrayOf( 0,1,0,0,0,0 ),//A
            arrayOf( 0,0,0,0,0,0 ),//B
            arrayOf( 0,0,0,0,0,0 ),//C
            arrayOf( 0,0,0,1,0,0 ),//D
            arrayOf( 0,0,0,0,0,1 ),//E
            arrayOf( 0,0,0,0,0,0 ) //F
        )


        //wyswietlanie grafu
        bttShow.setOnClickListener {

            tvOutPut.text = ""
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

                        tvOutPut.append("Połączenie " + rTx + " z " + cTx +"\n")
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