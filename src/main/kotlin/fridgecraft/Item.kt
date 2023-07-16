package fridgecraft

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

data class Item(
    val id: Int? = Random().nextInt(),
    val name: String,
    private var isSealed: Boolean = true,
    private var expirationDate: LocalDateTime
) {
    fun open() {
        this.isSealed = false
    }

    fun degrade() {
        if (this.isSealed)
            this.expirationDate = this.expirationDate.minusHours(1)
        else
            this.expirationDate = this.expirationDate.minusHours(5)
    }

    fun isExpired(): Boolean {
        return this.expirationDate.isBefore(LocalDateTime.now())
    }

    fun checkDaysRemainingForExpire(): Long {
        val today = LocalDateTime.now()
        return ChronoUnit.DAYS.between(today, this.expirationDate)
    }
}