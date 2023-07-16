import fridgecraft.Item
import fridgecraft.SmartFridge
import java.time.LocalDateTime

fun main(args: Array<String>) {
    val fridge = SmartFridge()

    val milk = Item(name = "Milk", expirationDate = LocalDateTime.now().plusDays(1))
    val cheese = Item(name = "Cheese", expirationDate = LocalDateTime.now().plusDays(2))

    fridge.open()
    fridge.addItem(milk)
    fridge.close()

    fridge.open()
    milk.open()
    fridge.close()

    fridge.open()
    fridge.addItem(cheese)
    fridge.close()

    fridge.open()
    cheese.open()
    fridge.close()

    val startTime = System.currentTimeMillis()
    val testDuration = 60000

    while (System.currentTimeMillis() - startTime < testDuration) {
        fridge.open()
        fridge.listItems()
        fridge.close()

        Thread.sleep(1000)
    }
}