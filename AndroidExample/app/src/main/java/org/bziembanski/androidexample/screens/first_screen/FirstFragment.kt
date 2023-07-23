package org.bziembanski.androidexample.screens.first_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import org.bziembanski.androidexample.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private val viewModel: FirstViewModel by viewModels()
    private val adapter: PeopleAdapter = PeopleAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFirstBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.adapter = adapter
        binding.peopleList.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
        viewModel.people.observe(viewLifecycleOwner) {
            adapter.updateList(it)
            if (it.isNotEmpty()) {
                binding.loader.visibility = GONE
                binding.peopleList.visibility = VISIBLE
            } else {
                binding.loader.visibility = VISIBLE
                binding.peopleList.visibility = GONE
            }
        }
        return binding.root
    }
}