package com.example.wishlist

class ShoppingList() {
    private val shoppingList: MutableList<ShoppingListItem>
    init {
        shoppingList = ArrayList<ShoppingListItem>()
    }
    fun addItem(
        itemName: String,
        itemPrice: Number,
        itemLink: String) {
        shoppingList.add(ShoppingListItem(itemName, itemPrice, itemLink))
    }

    fun getItems(): MutableList<ShoppingListItem> {
        return shoppingList
    }

    fun removeItem(index: Int) {
        shoppingList.removeAt(index)
    }

}