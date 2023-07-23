package org.bziembanski.androidexample.screens.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import org.bziembanski.androidexample.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailsBinding.inflate(inflater)
        viewModel.getPerson(args.personId)
        viewModel.person.observe(viewLifecycleOwner) {
            Log.d("DetailsFragment", it.toString())
            binding.apply {
                person = it
                if(person!=null){
                    loader.visibility = View.GONE
                    personInfoList.visibility = View.VISIBLE
                }
                else{
                    loader.visibility = View.VISIBLE
                    personInfoList.visibility = View.GONE
                }
            }
        }
        return binding.root
    }

}