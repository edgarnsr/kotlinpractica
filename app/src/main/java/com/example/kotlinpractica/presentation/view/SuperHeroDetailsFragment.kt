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
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 * Use the [SuperHeroDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SuperHeroDetailsFragment : Fragment() {

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
            binding.txtVieName.text = it.name
            binding.txtViewIntelligence.text = it.powerStats.intelligence
            binding.txtVieStrength.text = it.powerStats.strength
            binding.txtVieSpeed.text = it.powerStats.speed
            binding.txtVieDurability.text = it.powerStats.durability
            binding.txtViePower.text = it.powerStats.power
            binding.txtVieCombat.text = it.powerStats.combat
            binding.txtVieFullName.text = it.biography.fullName
            binding.txtVieAlterEgos.text = it.biography.alterEgos
            binding.txtVieAliases.text = it.biography.aliases.joinToString(separator = ", ")
            binding.txtViewPlaceOfBirth.text = it.biography.placeOfBirth
            binding.txtViewFirsyAppearance.text = it.biography.firstAppearance
            binding.txtViewPublisher.text = it.biography.publisher
            binding.txtViewAligment.text = it.biography.alignment
            binding.txtViewGender.text = it.appearance.gender
            binding.txtViewRace.text = it.appearance.race
            binding.txtViewHeight.text = it.appearance.height.joinToString(separator = ", ")
            binding.txtViewWeight.text = it.appearance.weight.joinToString(separator = ", ")
            binding.txtVieEyeColor.text = it.appearance.eyeColor
            binding.txtVieHairColor.text = it.appearance.hairColor
            binding.txtVieOccupation.text = it.work.occupation
            binding.txtVieBase.text = it.work.base
            binding.txtViewGroupAffiliation.text = it.connections.groupAffiliation
            binding.txtViewRelatives.text = it.connections.relatives
            Picasso.get().load(it.image.url).into(binding.imageUrl);
        }
        superHeroViewModel.setSuperHeroDetail(arguments?.getString("superHeroModel").toString())
    }
}