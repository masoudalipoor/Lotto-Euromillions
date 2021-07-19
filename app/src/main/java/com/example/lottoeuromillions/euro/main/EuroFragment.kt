package com.example.lottoeuromillions.euro.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lottoeuromillions.R
import com.example.lottoeuromillions.ViewType
import com.example.lottoeuromillions.databinding.FragmentEuroBinding
import com.example.lottoeuromillions.euro.Euro
import com.example.lottoeuromillions.euro.EuroAdapter
import com.example.lottoeuromillions.euro.Lucky
import com.example.lottoeuromillions.euro.LuckyAdapter


class EuroFragment : Fragment(), (Any) -> Unit {
    private var _binding: FragmentEuroBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<EuroViewModel>()
    private lateinit var euroAdapter: EuroAdapter
    private lateinit var luckyAdapter: LuckyAdapter
    private val euroItems = ArrayList<Euro>()
    private val luckyItems = ArrayList<Lucky>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEuroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEuroRecyclerview()
        initLuckyRecyclerview()
        binding.toolbarEuro.inflateMenu(R.menu.menu_euro)
        binding.toolbarEuro.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.euroResultFragment -> {
                    findNavController().navigate(R.id.action_euroFragment_to_euroResultFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun initEuroRecyclerview() {
        binding.rvEuro.setHasFixedSize(true)
        euroAdapter = EuroAdapter(
            requireContext(),
            ViewType.MAIN,
            this
        )
        binding.rvEuro.adapter = euroAdapter

        if (euroItems.size > 0) euroItems.clear()
        for (i in 1..50) {
            euroItems.add(Euro(i, 0))
        }
        euroAdapter.submitList(euroItems)
    }

    private fun initLuckyRecyclerview() {
        binding.rvLucky.setHasFixedSize(true)
        luckyAdapter =
            LuckyAdapter(
                requireContext(),
                ViewType.MAIN,
                this
            )
        binding.rvLucky.adapter = luckyAdapter

        if (luckyItems.size > 0) luckyItems.clear()
        for (i in 1..12) {
            luckyItems.add(Lucky(i, 0))
        }
        luckyAdapter.submitList(luckyItems)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun invoke(any: Any) {
        when(any){
            is Euro -> {viewModel.saveEuro(any.number)}
            is Lucky -> {viewModel.saveLucky(any.number)}
        }
    }
}