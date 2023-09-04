package org.bziembanski.androidexample.screens.third_screen

import android.os.Bundle
import android.util.Log
import android.util.TimingLogger
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.bziembanski.androidexample.R
import org.bziembanski.androidexample.screens.third_screen.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class ThirdFragment : Fragment() {

  private var columnCount = 1

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    arguments?.let {
      columnCount = it.getInt(ARG_COLUMN_COUNT)
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_third_list, container, false)
    var timings = TimingLogger("TAG", "scrollTime")
    // Set the adapter
    if (view is RecyclerView) {
      with(view) {
        layoutManager = when {
          columnCount <= 1 -> LinearLayoutManager(context)
          else -> GridLayoutManager(context, columnCount)
        }
        adapter = ThirdRecyclerViewAdapter(PlaceholderContent.ITEMS)
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
          override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
              timings = TimingLogger("TAG", "scrollTime")
              timings.addSplit("start")
            } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
              timings.addSplit("stop")
              timings.dumpToLog()
            }
          }
        })
        post {
          smoothScrollToPosition(PlaceholderContent.COUNT)
        }
      }
    }
    return view
  }

  companion object {

    // TODO: Customize parameter argument names
    const val ARG_COLUMN_COUNT = "column-count"

    // TODO: Customize parameter initialization
    @JvmStatic
    fun newInstance(columnCount: Int) =
      ThirdFragment().apply {
        arguments = Bundle().apply {
          putInt(ARG_COLUMN_COUNT, columnCount)
        }
      }
  }
}