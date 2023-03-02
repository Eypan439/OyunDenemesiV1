package com.eypancakir.oyundenemesiv1

class Board(private val size: Int) {
    private val cells = Array(size) { IntArray(size) { 0 } }
    private val owners = Array(size) { IntArray(size) { 0 } }

    init {
        generateCells()
    }

    private fun generateCells() {
        val totalCells = size * size
        val cellsPerPlayer = totalCells / players.size

        // Distribute cells randomly among players
        val shuffledPlayers = players.shuffled()
        for (i in 0 until players.size) {
            val player = shuffledPlayers[i]
            val cellsToAssign = cellsPerPlayer + if (i == 0) totalCells % players.size else 0

            repeat(cellsToAssign) {
                var x: Int
                var y: Int
                do {
                    x = (0 until size).random()
                    y = (0 until size).random()
                } while (owners[x][y] != 0)

                cells[x][y] = (1..6).random() // randomly assign a value to the cell
                owners[x][y] = player.id
                player.addOwnedCell(Cell(x, y, cells[x][y]))
            }
        }
    }

    fun getCellOwner(x: Int, y: Int): Int {
        return owners[x][y]
    }

    fun getCellValue(x: Int, y: Int): Int {
        return cells[x][y]
    }

    fun setCellOwner(x: Int, y: Int, playerId: Int) {
        owners[x][y] = playerId
    }
}
