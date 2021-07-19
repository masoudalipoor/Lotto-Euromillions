package com.example.lottoeuromillions.euro.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.lottoeuromillions.ViewType
import com.example.lottoeuromillions.databinding.FragmentEuroResultBinding
import com.example.lottoeuromillions.euro.EuroAdapter
import com.example.lottoeuromillions.euro.LuckyAdapter

class EuroResultFragment : Fragment(), (String, Int) -> Unit {
    private var _binding: FragmentEuroResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<EuroResultViewModel>()
    private lateinit var euroResultAdapter: EuroAdapter
    private lateinit var luckyResultAdapter: LuckyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEuroResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerview()

        viewModel.getAllEuro().observe(viewLifecycleOwner, Observer {
            euroResultAdapter.submitList(it)
        })

        viewModel.getAllLucky().observe(viewLifecycleOwner, Observer {
            luckyResultAdapter.submitList(it)
        })

        binding.toolbarEuroResult.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initRecyclerview() {
        euroResultAdapter = EuroAdapter(requireContext(), ViewType.RESULT, {}, this)
        binding.rvEuroResult.adapter = euroResultAdapter

        luckyResultAdapter = LuckyAdapter(
            requireContext(),
            ViewType.RESULT,
            {},
            this
        )
        binding.rvLuckyResult.adapter = luckyResultAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun invoke(type: String, number: Int) {
        when (type) {
            "Euro" -> {
                viewModel.subFromEuro(number)
            }
            "Lucky" -> {
                viewModel.subFromLucky(number)
            }
        }
    }
}