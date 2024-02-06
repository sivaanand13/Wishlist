package com.example.wishlist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var items: List<ShoppingListItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        val name = findViewById<EditText>(R.id.newItemName)
        val price = findViewById<EditText>(R.id.newItemPrice)
        val link = findViewById<EditText>(R.id.newItemLink)
        val addItemButton = findViewById<Button>(R.id.addItemButton)

        val recyclerView = findViewById<RecyclerView>(R.id.shoppingListRV)

        // create a shopping list
        val shoppingList =  ShoppingList()

        val shoppingListAdapter = ItemAdapter(shoppingList)
        recyclerView.adapter = shoppingListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addItemButton.setOnClickListener{

            try {
                shoppingList.addItem(
                    name.text.toString(),
                    NumberFormat.getInstance().parse(price.text.toString()),
                    link.text.toString()
                )
                shoppingListAdapter.notifyDataSetChanged()
            } catch (error: Exception) {
                Toast.makeText(this, "Invalid fields!", Toast.LENGTH_SHORT).show()
            }
            name.text.clear()
            price.text.clear()
            link.text.clear()

            // minimize the keyboard
            val thisView = this.currentFocus
            val inputManger = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManger.hideSoftInputFromWindow(thisView?.windowToken, 0)
        }

        addItemButton.setOnFocusChangeListener{
            view, hasFocus ->

            if (!hasFocus) {
                val thisView = this.currentFocus
                val inputManger = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManger.hideSoftInputFromWindow(thisView?.windowToken, 0)
            }
        }


    }
}
