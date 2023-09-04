package org.bziembanski.androidexample.screens.first_screen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.bziembanski.androidexample.R
import org.bziembanski.androidexample.data.Person
import org.bziembanski.androidexample.databinding.PersonRowBinding

class PeopleAdapter(private var people: List<Person>) :
  RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

  override fun getItemCount(): Int = people.size

  @SuppressLint("NotifyDataSetChanged")
  fun updateList(list: List<Person>) {
    people = list
    notifyDataSetChanged()
  }

  class ViewHolder(private val personRowBinding: PersonRowBinding) :
    RecyclerView.ViewHolder(personRowBinding.root) {

    fun bind(person: Person) {
      personRowBinding.person = person
      personRowBinding.root.setOnClickListener {
        val id = person.url.split("/").last {
          it.isNotEmpty()
        }.toInt()
        val action = FirstFragmentDirections
          .actionFirstFragmentToDetailsFragment(id)
        personRowBinding.root.findNavController().navigate(action)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding: PersonRowBinding = DataBindingUtil.inflate(
      LayoutInflater.from(parent.context),
      R.layout.person_row,
      parent,
      false,
    )

    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val person = people[position]
    holder.bind(person)
  }
}