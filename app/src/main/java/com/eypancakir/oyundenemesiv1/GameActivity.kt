package com.eypancakir.oyundenemesiv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class GameActivity : AppCompatActivity() {

    private lateinit var boardView: BoardView
    private lateinit var currentPlayerTextView: TextView
    private lateinit var requestLandEditText: EditText
    private lateinit var requestLandButton: Button

    private var currentPlayerIndex = 0
    private lateinit var currentPlayer: Player
    private lateinit var players: List<Player>
    private lateinit var board: Board

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        boardView = findViewById(R.id.boardView)
        currentPlayerTextView = findViewById(R.id.currentPlayerTextView)
        requestLandEditText = findViewById(R.id.requestLandEditText)
        requestLandButton = findViewById(R.id.requestLandButton)

        // Board, players ve currentPlayer nesnelerini oluşturma kodu buraya gelecek.

        boardView.setBoard(board)
        boardView.setOnClickListener { handleBoardClick() }

        requestLandButton.setOnClickListener { handleRequestLand() }

        currentPlayer = players[currentPlayerIndex]
        updateUI()
    }

    private fun handleBoardClick() {
        // BoardView'dan seçilen toprağı işaretlemek için gerekli kod buraya gelecek.
    }

    private fun handleRequestLand() {
        val requestedLandCount = requestLandEditText.text.toString().toIntOrNull()

        if (requestedLandCount != null) {
            val hasEnoughLands = currentPlayer.landCount >= requestedLandCount
            if (hasEnoughLands) {
                currentPlayer.requestedLands = requestedLandCount
                currentPlayer.setIsWaitingForResponse(true)
                passTurn()
            } else {
                Toast.makeText(this, "Yeterli toprağın yok!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Geçersiz sayı!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun passTurn() {
        currentPlayerIndex++
        if (currentPlayerIndex >= players.size) {
            currentPlayerIndex = 0
        }

        currentPlayer = players[currentPlayerIndex]
        updateUI()

        if (currentPlayer.isComputer) {
            val computerPlayer = currentPlayer as ComputerPlayer
            computerPlayer.makeMove()
            passTurn()
        }
    }

    private fun updateUI() {
        currentPlayerTextView.text = "Sıra ${currentPlayer.name}'de"

        requestLandEditText.setText("")
        requestLandEditText.hint = "Kaç toprak istiyorsun, ${currentPlayer.name}?"

        requestLandButton.isEnabled = !currentPlayer.isComputer && !currentPlayer.isWaitingForResponse
    }

}
