package ru.larineldar.filmsearch.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.larineldar.filmsearch.databinding.FragmentWatchLaterBinding
import ru.larineldar.filmsearch.utils.AnimationHelper

class WatchLaterFragment : Fragment() {
    private lateinit var binding : FragmentWatchLaterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchLaterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), POSITION_IN_BOTTOM_BAR)

    }

    companion object{
        const val POSITION_IN_BOTTOM_BAR = 3
    }
}