package com.eypancakir.oyundenemesiv1

class PieceAdapter(private val context: Context, private val pieces: List<GamePiece>) :
    RecyclerView.Adapter<PieceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PieceViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_piece, parent, false)
        return PieceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PieceViewHolder, position: Int) {
        val piece = pieces[position]
        val imageResource = if (piece.player == Player.FIRST_PLAYER) {
            R.drawable.ic_x
        } else {
            R.drawable.ic_o
        }
        holder.bind(imageResource)
    }

    override fun getItemCount(): Int {
        return pieces.size
    }
}
