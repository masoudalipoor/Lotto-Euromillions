package com.example.lottoeuromillions.lotto.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lottoeuromillions.R
import com.example.lottoeuromillions.ViewType
import com.example.lottoeuromillions.databinding.FragmentLottoBinding
import com.example.lottoeuromillions.lotto.Lotto
import com.example.lottoeuromillions.lotto.LottoAdapter

class LottoFragment : Fragment(), (Int) -> Unit {
    private var _binding: FragmentLottoBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LottoViewModel>()
    private lateinit var lottoAdapter: LottoAdapter
    private val items = ArrayList<Lotto>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLottoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerview()
        binding.toolbarLotto.inflateMenu(R.menu.menu_lotto)
        binding.toolbarLotto.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.resultFragment -> {
                    findNavController().navigate(R.id.action_lottoFragment_to_lottoResultFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun initRecyclerview() {
        binding.rvLotto.setHasFixedSize(true)
        lottoAdapter =
            LottoAdapter(
                requireContext(),
                ViewType.MAIN,
                this
            )
        binding.rvLotto.adapter = lottoAdapter

        if(items.size > 0) items.clear()
        for (i in 1..59) {
            items.add(Lotto(i, 0))
        }
        lottoAdapter.submitList(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun invoke(number: Int) {
        viewModel.saveLotto(number)
    }
}