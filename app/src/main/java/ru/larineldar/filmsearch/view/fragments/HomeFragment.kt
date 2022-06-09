package ru.larineldar.filmsearch.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import ru.larineldar.filmsearch.domain.Film
import ru.larineldar.filmsearch.view.rv_adapters.FilmAdapter
import ru.larineldar.filmsearch.R
import ru.larineldar.filmsearch.view.rv_adapters.SpacingItemDecoration
import ru.larineldar.filmsearch.databinding.FragmentHomeBinding
import ru.larineldar.filmsearch.utils.AnimationHelper
import ru.larineldar.filmsearch.viewmodel.HomeFragmentViewModel
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val adapter = FilmAdapter()
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }

    private var filmsDataBase = listOf<Film>()
        set(value) {
            if(value == field) return

            field = value
            adapter.addItems(value)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.filmsListLiveData.observe(viewLifecycleOwner){
            filmsDataBase = it
        }

        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 1)
    }

    private fun initView() {
        val searchView = binding.searchView
        val recyclerView = binding.recyclerView


        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(SpacingItemDecoration(8))

        searchView.setOnClickListener {
            searchView.isIconified = false
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                val result = filmsDataBase.filter {
                    it.title.toLowerCase(Locale.getDefault())
                        .contains(newText.toLowerCase(Locale.getDefault()))
                }
                adapter.addItems(result)
                return true
            }

        })
    }
}