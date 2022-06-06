package ru.larineldar.filmsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ru.larineldar.filmsearch.databinding.FragmentFavoritesBinding

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
        recyclerView.addItemDecoration(SpacingItemDecoration(8))

        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 2)
    }
}