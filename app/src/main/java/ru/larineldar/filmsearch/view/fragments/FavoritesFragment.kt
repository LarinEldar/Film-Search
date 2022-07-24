package ru.larineldar.filmsearch.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.larineldar.filmsearch.view.rv_adapters.FilmAdapter
import ru.larineldar.filmsearch.view.rv_adapters.SpacingItemDecoration
import ru.larineldar.filmsearch.databinding.FragmentFavoritesBinding
import ru.larineldar.filmsearch.utils.AnimationHelper

class FavoritesFragment: Fragment() {
    private lateinit var binding : FragmentFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.favoritesRecycler
        recyclerView.adapter = FilmAdapter()
        recyclerView.addItemDecoration(SpacingItemDecoration(HomeFragment.RV_PADDING_IN_DP))

        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), POSITION_IN_BOTTOM_BAR)
    }

    companion object{
        const val POSITION_IN_BOTTOM_BAR = 2
    }
}