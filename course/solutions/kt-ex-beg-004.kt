package academy.kotlin.solutions

// Value classها اجازه نمی‌دهند شناسه‌های دامنه‌ای هم‌شکل تصادفاً جابه‌جا شوند.
@JvmInline value class UserId(val value: Long)
@JvmInline value class OrderId(val value: Long)

fun loadUser(id: UserId): String = "user:${id.value}"
fun loadOrder(id: OrderId): String = "order:${id.value}"
