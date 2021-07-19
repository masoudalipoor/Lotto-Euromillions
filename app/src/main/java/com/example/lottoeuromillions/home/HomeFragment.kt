package com.example.lottoeuromillions.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lottoeuromillions.R
import com.example.lottoeuromillions.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStartLotto.setOnClickListener(this)
        binding.btnStartEuro.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnStartLotto -> {
                findNavController().navigate(R.id.action_homeFragment_to_lottoFragment)
            }

            R.id.btnStartEuro -> {
                findNavController().navigate(R.id.action_homeFragment_to_euroFragment)
            }
        }
    }
}