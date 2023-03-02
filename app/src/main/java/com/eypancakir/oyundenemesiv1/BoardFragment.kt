package com.eypancakir.oyundenemesiv1

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager

class BoardFragment : Fragment() {

    private lateinit var boardViewModel: BoardViewModel
    private lateinit var boardAdapter: BoardAdapter
    private lateinit var boardLayoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBoardBinding.inflate(inflater, container, false)

        boardViewModel = ViewModelProvider(this).get(BoardViewModel::class.java)
        boardAdapter = BoardAdapter(requireContext())
        boardLayoutManager = GridLayoutManager(requireContext(), 8)

        binding.recyclerView.apply {
            adapter = boardAdapter
            layoutManager = boardLayoutManager
        }

        boardViewModel.pieces.observe(viewLifecycleOwner, {
            boardAdapter.setPieces(it)
        })

        return binding.root
    }
}

