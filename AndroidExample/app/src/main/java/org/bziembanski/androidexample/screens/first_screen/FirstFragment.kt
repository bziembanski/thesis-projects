package org.bziembanski.androidexample.screens.first_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import org.bziembanski.androidexample.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private val viewModel: FirstViewModel by viewModels()
    private val adapter: PeopleAdapter = PeopleAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFirstBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.adapter = adapter
        viewModel.people.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })
        return binding.root
    }
}