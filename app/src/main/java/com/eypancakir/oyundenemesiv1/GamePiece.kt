package com.eypancakir.oyundenemesiv1

class GamePiece(val player: Player, val row: Int, val col: Int) {

    override fun equals(other: Any?): Boolean {
        if (other !is GamePiece) {
            return false
        }

        return this.player == other.player && this.row == other.row && this.col == other.col
    }

    override fun hashCode(): Int {
        var result = player.hashCode()
        result = 31 * result + row
        result = 31 * result + col
        return result
    }
}
