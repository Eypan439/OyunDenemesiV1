package com.eypancakir.oyundenemesiv1

class GameLogic(private val board: Board) {
    private var currentPlayer = Player.FIRST_PLAYER
    private var winner: Player? = null

    fun getCurrentPlayer(): Player {
        return currentPlayer
    }

    fun getWinner(): Player? {
        return winner
    }

    fun hasGameEnded(): Boolean {
        return winner != null || board.isBoardFull()
    }

    fun playPiece(row: Int, col: Int): Boolean {
        if (!board.isCellEmpty(row, col)) {
            return false
        }

        val piece = GamePiece(currentPlayer, row, col)
        board.placePiece(piece)

        if (board.hasPlayerWon(currentPlayer)) {
            winner = currentPlayer
        } else {
            currentPlayer = if (currentPlayer == Player.FIRST_PLAYER) {
                Player.SECOND_PLAYER
            } else {
                Player.FIRST_PLAYER
            }
        }
        return true
    }

    fun reset() {
        currentPlayer = Player.FIRST_PLAYER
        winner = null
        board.reset()
    }
}
