package org.bziembanski.androidexample.screens.third_screen.placeholder

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {

  /**
   * An array of sample (placeholder) items.
   */
  val ITEMS: MutableList<PlaceholderItem> = ArrayList()

  /**
   * A map of sample (placeholder) items, by ID.
   */
  private val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

  const val COUNT = 1000

  init {
    // Add some sample items.
    for (i in 1..COUNT) {
      addItem(createPlaceholderItem(i))
    }
  }

  private fun addItem(item: PlaceholderItem) {
    ITEMS.add(item)
    ITEM_MAP[item.id] = item
  }

  private fun createPlaceholderItem(position: Int): PlaceholderItem {
    return PlaceholderItem(position.toString())
  }

  private fun makeDetails(position: Int): String {
    val builder = StringBuilder()
    builder.append("Details about Item: ").append(position)
    for (i in 0..<position) {
      builder.append("\nMore details information here.")
    }
    return builder.toString()
  }

  /**
   * A placeholder item representing a piece of content.
   */
  data class PlaceholderItem(val id: String) {
    override fun toString(): String = id
  }
}