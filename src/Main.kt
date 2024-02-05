data class InventoryItem(val name: String, var count: Int)

class Inventory {
    private val items: MutableList<InventoryItem> = mutableListOf(
        InventoryItem("PS5", 0),
        InventoryItem("Xbox", 30),
        InventoryItem("Nintendo", 30),
        InventoryItem("VR", 30),
        InventoryItem("PC", 30)
    )

    fun searchItem(itemName: String): Int? {
        return items.find { it.name.equals(itemName, ignoreCase = true) }?.count
    }

    fun addItem(itemName: String, amount: Int): String {
        val item = items.find { it.name.equals(itemName, ignoreCase = true) }
        if (item != null) {
            val originalCount = item.count
            item.count += amount
            if (item.count < 0) {
                item.count = 0
                return "Error: Count cannot be less than 0. Count set to 0."
            }
            val addedOrTaken = if (amount >= 0) "added" else "taken away"
            return "Item: ${item.name}, Original Count: $originalCount, Amount $addedOrTaken: ${Math.abs(amount)}, New Total: ${item.count}"
        }
        return "Error: Item not found."
    }
}

fun main() {
    val inventory = Inventory()

    val searchResult = inventory.searchItem("PS5")
    println("Search Result for 'PS5': $searchResult")

    val addItemResult = inventory.addItem("PS5", 5)
    println(addItemResult)

    val subtractItemResult = inventory.addItem("PS5", -3)
    println(subtractItemResult)

    val subtractItemErrorResult = inventory.addItem("PS5", -5)
    println(subtractItemErrorResult)
}