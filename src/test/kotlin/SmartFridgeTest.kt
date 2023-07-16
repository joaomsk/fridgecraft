import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import fridgecraft.Item
import fridgecraft.SmartFridge
import java.time.LocalDateTime

class SmartFridgeTest {

    private lateinit var fridge: SmartFridge

    @BeforeEach
    fun setUp() {
        fridge = SmartFridge()
    }

    @Test
    fun `should open the fridge successfully`() {
        fridge.open()
        assertTrue(fridge.isOpen)
    }

    @Test
    fun `should close the fridge successfully`() {
        fridge.close()
        assertFalse(fridge.isOpen)
    }

    @Test
    fun `should add an item to the fridge successfully`() {
        fridge.open()
        fridge.addItem(milk)
        assertEquals(1, fridge.items.size)
    }

    @Test
    fun `should throw exception when trying to add an item if the door fridge is closed`() {
        fridge.addItem(cheese)
    }

    @Test
    fun `should close the fridge after add an item successfully`() {
        fridge.open()
        fridge.addItem(milk)
        assertFalse(fridge.isOpen)
    }

    @Test
    fun `should remove an item successfully`() {
        fridge.open()
        fridge.addItem(milk)
        fridge.removeItem(milk.name)
        assertEquals(0, fridge.items.size)
    }

    private var milk = Item(
        name = "Milk",
        expirationDate = LocalDateTime.now().plusMinutes(2)
    )

    private var cheese = Item(
        name = "Cheese",
        expirationDate = LocalDateTime.now().plusDays(14)
    )
}