package com.example.asgrafy

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import org.jetbrains.annotations.TestOnly

class MainActivity : AppCompatActivity() {

    lateinit var edtCol : EditText
    lateinit var edtRow : EditText
    lateinit var edtConn: EditText
    lateinit var bttConn: Button
    lateinit var bttDijk: Button
    lateinit var bttPaths: Button
    lateinit var bttShowG: Button
    lateinit var tvOutPut:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtCol = findViewById(R.id.edtWezelKol)
        edtRow = findViewById(R.id.edtWezelWier)
        edtConn= findViewById(R.id.edtPolaczenie)
        bttConn= findViewById(R.id.bttPolaczenieZat)
        bttDijk= findViewById(R.id.bttDijkstra)
        bttPaths= findViewById(R.id.bttGraph)
        bttShowG= findViewById(R.id.bttGraphFull)
        tvOutPut=findViewById(R.id.tvOutPut)

        //deklaracja grafu
        var arrGreg = arrayOf(
                   //A,B,C,D,E,F
            arrayOf( 1,1,1,1,1,0 ),//A
            arrayOf( 0,1,0,0,1,1 ),//B
            arrayOf( 1,1,1,0,0,1 ),//C
            arrayOf( 1,0,0,1,1,1 ),//D
            arrayOf( 1,1,0,1,0,0 ),//E
            arrayOf( 0,1,1,1,1,1 ) //F
        )//order : arrGreg[row][column]


        //wyswietlanie połączeń grafu
        bttPaths.setOnClickListener {

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
        }//bttPaths

        bttShowG.setOnClickListener {
            tvOutPut.text = ""
            /*
            for (r in arrGreg){
                tvOutPut.append(arrGreg[r.toString().toInt()].toString())
            }
             */
            for (r in 0..5) {
                for (c in 0..5) {
                    tvOutPut.append(arrGreg[r][c].toString() + " ")
                }
                tvOutPut.append("\n")
            }
        }//bttGraphFull


        edtCol.addTextChangedListener {
            if(edtCol.text.isNotEmpty()){
                val colValue : Int = edtCol.text.toString().toInt()
                val rowValue : Int = edtRow.text.toString().toInt()

                if (colValue !in 0..5 || rowValue !in 0..5){
                    //val maxVal = 5
                    //edtCol.setText(maxVal)

                    val toastText = "Graf posiada 6 wierzchołków (A-F). Proszę wprowadzić wartość od 0 do 5"
                    val toast = Toast.makeText(applicationContext, toastText, Toast.LENGTH_LONG)
                    toast.show()
                    edtConn.setText("")

                    return@addTextChangedListener
                }

                val nodeInfo : String = arrGreg[rowValue][colValue].toString()
                edtConn.setText(nodeInfo)
            }else{
                edtConn.setText("")
            }
        }//edtCol

        edtRow.addTextChangedListener {
            if (edtRow.text.isNotEmpty()) {
                val colValue : Int = edtCol.text.toString().toInt()
                val rowValue : Int = edtRow.text.toString().toInt()

                if (rowValue !in 0..5 || colValue !in 0..5){
                    //val maxVal = 5
                    //edtRow.setText(maxVal)

                    val toastText = "Graf posiada 6 wierzchołków (A-F). Proszę wprowadzić wartość od 0 do 5"
                    val toast = Toast.makeText(applicationContext, toastText, Toast.LENGTH_LONG)
                    toast.show()
                    edtConn.setText("")

                    return@addTextChangedListener
                }

                val nodeInfo : String = arrGreg[rowValue][colValue].toString()
                edtConn.setText(nodeInfo)
            }else{
                edtConn.setText("")
            }
        }//edtRow

        bttConn.setOnClickListener {

            val colValue : Int = edtCol.text.toString().toInt()
            val rowValue : Int = edtRow.text.toString().toInt()
            val gotConnectionValue : Int = edtConn.text.toString().toInt()

            if (colValue in 0..5 && rowValue in 0..5){
                if (gotConnectionValue == 1 || gotConnectionValue == 0){
                    arrGreg[rowValue][colValue] = gotConnectionValue
                }else{
                    val toastText = "Połączenie musi mieć wartość 0 lub 1"
                    val toast = Toast.makeText(applicationContext, toastText, Toast.LENGTH_SHORT)
                    toast.show()
                }
            }else{
                val toastText = "Wartość wierzchołków musi być w przedziale od 0 do 5"
                val toast = Toast.makeText(applicationContext, toastText, Toast.LENGTH_SHORT)
                toast.show()
            }

        }//bttConn

        bttDijk.setOnClickListener {

            val weights = mapOf(
                Pair("A", "B") to 2,
                Pair("A", "C") to 8,
                Pair("A", "D") to 5,
                Pair("B", "C") to 1,
                Pair("C", "E") to 3,
                Pair("D", "E") to 2
            )

            val startingPoint = "A"
            val shortestPathTree = dijkstra(Graph(weights = weights, vertices = setOf(), edges = mapOf()),startingPoint)

            tvOutPut.setText("")
            tvOutPut.append(shortestPath(shortestPathTree,startingPoint,"C").toString())

        }//bttDijkstra

    }//OnCreate

    //pierwszy algorytm

    data class Graph<T>(
        val vertices: Set<T>,
        val edges: Map<T,Set<T>>,
        val weights: Map<Pair<T,T>,Int>
    )

    fun <T> dijkstra(graph: Graph<T>, startPoint: T): Map<T,T?>{
        val subSet: MutableSet<T> = mutableSetOf()
        val delta = graph.vertices.map { it to Int.MAX_VALUE }.toMap().toMutableMap()
        delta[startPoint] = 0

        val previous: MutableMap<T,T?> = graph.vertices.map { it to null }.toMap().toMutableMap()

        while (subSet != graph.vertices) {
            val v: T = delta
                .filter { !subSet.contains(it.key) }
                .minBy { it.value }!!
                .key

            graph.edges.getValue(v).minus(subSet).forEach { neighbor ->
                val newPath = delta.getValue(v) + graph.weights.getValue(Pair(v,neighbor))

                if (newPath < delta.getValue(neighbor)){
                    delta[neighbor] = newPath
                    previous[neighbor] = v
                }
            }

            subSet.add(v)
        }
        return previous.toMap()
    }

    fun <T> shortestPath(shortestPathTree: Map<T, T?>, start: T, end: T): List<T> {
        fun pathTo(start: T, end: T): List<T> {
            if (shortestPathTree[end] == null) return listOf(end)
            return listOf(pathTo(start, shortestPathTree[end]!!), listOf(end)).flatten()
        }
        return pathTo(start, end)
    }

}