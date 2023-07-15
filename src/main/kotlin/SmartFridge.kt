import java.time.LocalDateTime

data class SmartFridge(
    val items: MutableList<Item> = mutableListOf(),
    var isOpen: Boolean = false
) {
    fun open() {
        println("Opening fridge... ${LocalDateTime.now()}")
        this.isOpen = true
        repeat(items.size) { this.degradeItemsWhenFridgeIsOpen() }
    }

    fun close() {
        println("Closing fridge... ${LocalDateTime.now()}")
        this.isOpen = false
    }

    fun addItem(item: Item) {
        when {
            this.isOpen -> {
                items.add(item)
                this.close()
                println("Added item: $item")
            }

            else -> {
                println("Fridge is closed. Please open fridge to add item.")
            }
        }
    }

    fun removeItem(itemName: String) {
        items.removeIf { it.name == itemName }
        println("Removed item: $itemName")
    }

    fun getItem(itemName: String): Item? {
        println("Getting item: $itemName")
        return items.find { it.name == itemName }
    }

    private fun degradeItemsWhenFridgeIsOpen() {
        items.forEach { it.degrade() }
    }
}