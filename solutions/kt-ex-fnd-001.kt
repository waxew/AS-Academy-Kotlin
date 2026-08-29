package academy.kotlin.solutions

/**
 * راه‌حل مرجع تمرین kt-ex-fnd-001.
 * require ورودی‌های خارج از قرارداد تابع را در همان مرز ورودی رد می‌کند.
 */
fun finalPrice(price: Long, discountPercent: Int): Long {
    require(price >= 0) { "price must be non-negative" }
    require(discountPercent in 0..100) { "discount must be between 0 and 100" }
    return price - (price * discountPercent / 100)
}
