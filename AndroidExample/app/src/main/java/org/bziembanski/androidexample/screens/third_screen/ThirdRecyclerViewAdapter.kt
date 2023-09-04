package org.bziembanski.androidexample.screens.third_screen

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import org.bziembanski.androidexample.databinding.FragmentThirdBinding
import org.bziembanski.androidexample.screens.third_screen.placeholder.PlaceholderContent.PlaceholderItem
import kotlin.random.Random

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ThirdRecyclerViewAdapter(
  private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<ThirdRecyclerViewAdapter.ViewHolder>() {
  private val colorRange = (0..255)
  private val radiusRange = (0..128)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    return ViewHolder(
      FragmentThirdBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )

  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.image.radius = TypedValue.applyDimension(
      TypedValue.COMPLEX_UNIT_DIP,
      radiusRange.random().toFloat(),
      holder.context.resources.displayMetrics
    )
    holder.imageView.setColorFilter(
      Color.pack(
        colorRange.random().toFloat(),
        colorRange.random().toFloat(),
        colorRange.random().toFloat()
    ).toColorInt(),
      PorterDuff.Mode.SCREEN
    )
  }

  override fun getItemCount(): Int = values.size

  inner class ViewHolder(binding: FragmentThirdBinding) : RecyclerView.ViewHolder(binding.root) {
    val imageView: ImageView = binding.imageView
    val image: CardView = binding.root
    val context = binding.root.context
  }

}