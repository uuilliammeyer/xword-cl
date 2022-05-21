import java.io.File
import java.io.InputStream

/*
function reads in the answer file, which we then use to make puzzle object
code via stackoverflow: https://stackoverflow.com/questions/55182578/how-to-read-plain-text-file-in-kotlin

 */
fun read(file: String): Triple<Array<CharArray>, MutableList<Pair<String, String>>, MutableList<Pair<String, String>>> {
    val inputStream: InputStream = File(file).inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }
//  get first time, which holds dimension data
    val lineOne = lineList[0].split(" ")
    val rows = lineOne[0].toInt()
    val cols = lineOne[1].toInt()

// https://stackoverflow.com/questions/34145495/2d-array-in-kotlin
//  create matrix to house solution to pass to puzzle
    var solution = Array(rows) { CharArray(cols) }
//    println("array is ${solution.size} tall and ${solution[0].size} wide")
//  For now, just using an array implementation for clues
    var acrossClues = mutableListOf<Pair <String, String>>()
    var downClues = mutableListOf<Pair<String, String>>()
//  read in puzzle
    for (line in 1 until rows + 1) {
//        if ( 0 < line &&  line < rows+1 ){
        for (char in 0 until lineList[line].length) {
            solution[(line - 1)][char] = lineList[line][char]
        }
    }
//  Gonna use this boolean for the hashmap
    var clueIsDown = false
    for (line in rows + 2 until lineList.size) {
        if (lineList[line] == "DOWN") {
            clueIsDown = true
        } else {
//            split string on -
            val clueInfo = lineList[line].split("-")

            val position:String = clueInfo[0]
            var clue:String = ""
            /* Maybe a bad idea rn
            but trying to make sure that our if someone wants to have a clue contain a dash that the
            clue is still treated as a single clue. */
            for (i in 1..clueInfo.lastIndex){
                clue += clueInfo[i]
            }

            val cluePair= Pair(position, clue)
            if (clueIsDown){
                downClues.add(cluePair)
            }
            else{
                acrossClues.add(cluePair)
            }

        }

//        } else {
//            acrossClues.add(lineList[line])
//        }
    }
    return Triple(solution, acrossClues, downClues)
}

fun main() {
//    TODO make calls either editor or puzzle depending on user selected mode
    println("Would you like to create/edit a puzzle or solve a puzzle")
    println("1: create or edit")
    println("2: Play a puzzle")
    var answer = readln()
//  Handle invalid answers
    while (answer != "1" && answer != "2") {
        println("Invalid option. Please select one of the follow: ")
        println("1: create or edit")
        println("2: Play a puzzle")
        answer = readln()
    }
//  Enter puzzle mode
    if (answer == "2") {
        println("specify file to puzzle to read in")
        val file = readln()
        val fileData = read(file)
//      grab info from read in file
        val solution = fileData.first
        val acrossClues = fileData.second
        val downClues = fileData.third

//        for (i in solution.indices){
//            for (j in solution[0].indices) {
//                print(solution[i][j])
//            }
//            println()
//        }

        val puz = Puzzle(solution, acrossClues, downClues)
        puz.play()
    }
//  Enter editor mode
    else {
        val e = Editor()
    }
}