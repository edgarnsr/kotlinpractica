package com.example.kotlinpractica.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinpractica.databinding.FragmentSuperHeroDetaitlsBinding
import com.example.kotlinpractica.presentation.viewmodel.SuperHeroViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [SuperHeroDetaitlsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SuperHeroDetaitlsFragment : Fragment() {

    private lateinit var binding: FragmentSuperHeroDetaitlsBinding

    private val superHeroViewModel: SuperHeroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSuperHeroDetaitlsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        superHeroViewModel.superHeroDetail.observe(viewLifecycleOwner) {
            binding.txtViewDetails.text = it
        }
        superHeroViewModel.setSuperHeroDetail(arguments?.getString("superHeroModel").toString())
    }
}