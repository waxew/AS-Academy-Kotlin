package academy.kotlin.examples

/**
 * نمونه‌ی پایه‌ی دوره Kotlin.
 * این فایل عمداً کوچک است تا هنرجو چرخه‌ی تعریف داده، تابع و اجرای برنامه را یکجا ببیند.
 */
data class Product(val name: String, val price: Long)

/** قیمت نهایی را با اعتبارسنجی درصد تخفیف محاسبه می‌کند. */
fun discountedPrice(price: Long, discountPercent: Int): Long {
    require(price >= 0) { "price must be non-negative" }
    require(discountPercent in 0..100) { "discount must be between 0 and 100" }
    return price - (price * discountPercent / 100)
}

/** نقطه ورود نمونه. */
fun main() {
    val product = Product(name = "Kotlin Course", price = 1_000_000)
    val finalPrice = discountedPrice(product.price, discountPercent = 15)
    println("${product.name}: $finalPrice")
}
