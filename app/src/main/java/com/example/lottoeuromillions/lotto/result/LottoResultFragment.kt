package com.example.lottoeuromillions.lotto.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.lottoeuromillions.ViewType
import com.example.lottoeuromillions.databinding.FragmentLottoResultBinding
import com.example.lottoeuromillions.lotto.LottoAdapter

class LottoResultFragment : Fragment(), (Int) -> Unit {
    private var _binding: FragmentLottoResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: LottoAdapter
    private val viewModel by viewModels<LottoResultViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLottoResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerview()
        binding.toolbarLottoResult.setNavigationOnClickListener { requireActivity().onBackPressed() }

        viewModel.getAllLotto().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun initRecyclerview() {
        binding.rvResult.setHasFixedSize(true)
        adapter = LottoAdapter(requireContext(), ViewType.RESULT, {},this )
        binding.rvResult.adapter = adapter
    }

    override fun invoke(number: Int) {
        viewModel.sub(number)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}