package com.eypancakir.oyundenemesiv1

class Game(private val players: List<Player>, private val board: Board) {
    private var currentPlayerIndex = 0

    fun getCurrentPlayer(): Player = players[currentPlayerIndex]

    fun playRound(squareIndex: Int, requestedTiles: Int) {
        val currentPlayer = getCurrentPlayer()
        val requestedTilesValid = currentPlayer.requestTiles(requestedTiles, board.getSquare(squareIndex))

        if (requestedTilesValid) {
            val tileResult = currentPlayer.flipTile()
            when (tileResult) {
                TileResult.HEADS -> board.addTiles(currentPlayer, squareIndex, requestedTiles)
                TileResult.TAILS -> {
                    val nextPlayerIndex = (currentPlayerIndex + 1) % players.size
                    val nextPlayer = players[nextPlayerIndex]
                    board.removeTiles(nextPlayer, squareIndex, requestedTiles)
                }
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size
        } else {
            println("Invalid number of tiles requested.")
        }
    }

    fun isGameOver(): Boolean = players.count { it.getNumSquaresOwned() > 0 } <= 1

    fun getWinner(): Player? {
        if (!isGameOver()) {
            return null
        }
        return players.maxByOrNull { it.getNumSquaresOwned() }
    }
}
