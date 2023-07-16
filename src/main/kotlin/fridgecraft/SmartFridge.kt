package fridgecraft

import java.time.LocalDateTime

data class SmartFridge(
    val items: MutableList<Item> = mutableListOf(),
    var isOpen: Boolean = false
) {
    fun open() {
        println("Opening fridge... ${LocalDateTime.now()}")
        this.isOpen = true
        items.forEach { it.degrade() }
    }

    private fun degradeItemsWhenFridgeIsOpen() {
        items.forEach { it.degrade() }
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

    fun listItems() {
        showExpiredItems()
        showNotExpiredItems()
    }

    private fun showExpiredItems() {
        val expiredItems = items.filter { it.isExpired() }
        expiredItems.forEach { println("EXPIRED: ${it.name}") }
    }

    private fun showNotExpiredItems() {
        val remainingItems = items.filter { !it.isExpired() }
        remainingItems.forEach { println("${it.name}: ${it.checkDaysRemainingForExpire()} days remaining until expire") }
    }
}