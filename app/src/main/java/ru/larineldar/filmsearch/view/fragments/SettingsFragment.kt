package ru.larineldar.filmsearch.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.larineldar.filmsearch.R
import ru.larineldar.filmsearch.databinding.FragmentSettingsBinding
import ru.larineldar.filmsearch.viewmodel.SettingsFragmentViewModel

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var category: String
    private val viewModel: SettingsFragmentViewModel =
        ViewModelProvider.NewInstanceFactory().create(SettingsFragmentViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserverCategory()
    }

    override fun onResume() {
        super.onResume()
        when (category) {
            POPULAR_CATEGORY -> binding.settingsRadioGroup.check(R.id.radio_popular)
            TOP_RATED_CATEGORY -> binding.settingsRadioGroup.check(R.id.radio_top)
            UPCOMING_CATEGORY -> binding.settingsRadioGroup.check(R.id.radio_upcoming)
            NOW_PLAYING_CATEGORY -> binding.settingsRadioGroup.check(R.id.radio_playing)
        }

        binding.settingsRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.radio_popular -> viewModel.putCategory(POPULAR_CATEGORY)
                R.id.radio_playing -> viewModel.putCategory(NOW_PLAYING_CATEGORY)
                R.id.radio_top -> viewModel.putCategory(TOP_RATED_CATEGORY)
                R.id.radio_upcoming -> viewModel.putCategory(UPCOMING_CATEGORY)
            }
        }
    }

    private fun setObserverCategory() {
        viewModel.categoryLiveData.observe(viewLifecycleOwner) {
            category = it
        }
    }

    companion object {
        private const val POPULAR_CATEGORY = "popular"
        private const val TOP_RATED_CATEGORY = "top_rated"
        private const val UPCOMING_CATEGORY = "upcoming"
        private const val NOW_PLAYING_CATEGORY = "now_playing"
    }
}