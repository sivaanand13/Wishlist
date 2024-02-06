package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val shoppingList: ShoppingList): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private var shoppingItems: List<ShoppingListItem>

    init {
        shoppingItems = shoppingList.getItems()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var price: TextView
        var link: TextView

        init {
            name = itemView.findViewById(R.id.itemName)
            price = itemView.findViewById(R.id.itemPrice)
            link = itemView.findViewById(R.id.itemLink)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contactView = inflater.inflate(R.layout.item, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = shoppingItems.get(position)
        holder.name.text = item.itemName
        holder.price.text = item.itemPrice.toString()
        holder.link.text = item.itemLink

        holder.itemView.setOnLongClickListener{
            shoppingList.removeItem(position)
            shoppingItems = shoppingList.getItems()
            notifyDataSetChanged()
            return@setOnLongClickListener true
        }

        holder.itemView.setOnClickListener{
            try {
                val browser = Intent(Intent.ACTION_VIEW, Uri.parse((item.itemLink)))
                ContextCompat.startActivity(it.context, browser, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + item.itemName, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return shoppingItems.size
    }
}