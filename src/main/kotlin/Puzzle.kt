

class Puzzle (private val solution:Array<CharArray>, val acrossClues:List<Pair<String, String>>, val downClues:List<Pair<String, String>>) {
    val rows = solution.size
    val cols = solution[0].size
    var currentBoard = Array(rows) { CharArray(cols) }

    val whiteSquareEmoji: Int  = 0x2B1C // used to be 2B1C
    val blackSquareEmoji: Int = 0x2B1B
//    this strategy does mean that right now we do not support more than 9x9 grids.
//    I think I am okay with that for now
    val numbersMap: HashMap<String, Int> = hashMapOf("ONE" to 0x00B9, "TWO" to 0x00B2, "THREE" to 0x00B3,
"FOUR" to 0x2074, "FIVE" to 0x2075, "SIX" to 0x2076, "SEVEN" to 0x2077, "EIGHT" to 0x2078, "NINE" to 0x2079)

    fun play() {
        initBoard()
        guess()
//        while(currentGuess != solution){
//
//        }
    }

    fun initBoard():Unit {
        for (row in solution.indices){
            for (col in solution[row].indices){
                if (solution[row][col]=='!'){
//                  represents blocked space
                    currentBoard[row][col] = '!'
                }
                else{
//                  Represents unfilled space
                    currentBoard[row][col] = '?'
                }
            }
        }
    }

    fun assignClueNumbers(){
//  first assign down numbers
        for (clue in downClues){

        }
    }


    fun printBoard():Unit {
        for (i in currentBoard.indices){
            for (j in currentBoard[0].indices) {
                if (currentBoard[i][j] == '!'){
//                  print black block if blocked
                    print(getEmoji(blackSquareEmoji))
                }
                else if (currentBoard[i][j] == '?'){
//                  print white block if just empty
                    print(getEmoji(whiteSquareEmoji))
                }
                else{
                    print(currentBoard[i][j])
                }

            }
            println()
        }
    }
//  helper function for printing the square emojis
private fun getEmoji(unicode: Int): String {
        return String(Character.toChars(unicode))
    }

    fun guess(){
        println("Your current board is: ")
        printBoard()
    }
}
