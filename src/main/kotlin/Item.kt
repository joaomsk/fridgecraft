import java.time.LocalDateTime
import java.util.Random

data class Item(
    val id: Int? = Random().nextInt(),
    val name: String,
    var isSealed: Boolean = true,
    val expirationDate: LocalDateTime
) {
    fun open() {
        this.isSealed = false
    }

    fun degrade() {
        when {
            this.isSealed -> {
                this.expirationDate.minusHours(1)
            }

            else -> {
                this.expirationDate.minusHours(5)
            }
        }
    }

    private fun isItemExpired() {
        if (this.expirationDate.isBefore(LocalDateTime.now())) {
            println("Item $name is expired.")
        }
    }
}