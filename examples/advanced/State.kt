package academy.kotlin.examples.advanced

/**
 * مدل عمومی وضعیت بارگذاری.
 * sealed interface باعث می‌شود when بتواند تمام حالت‌های مجاز را به‌صورت exhaustive بررسی کند.
 */
sealed interface LoadState<out T> {
    /** عملیات هنوز نتیجه‌ای ندارد. */
    data object Idle : LoadState<Nothing>

    /** عملیات در حال اجراست. */
    data object Loading : LoadState<Nothing>

    /** عملیات با داده موفق شده است. */
    data class Success<T>(val data: T) : LoadState<T>

    /** عملیات با پیام خطا پایان یافته است. */
    data class Error(val message: String) : LoadState<Nothing>
}

/** وضعیت را به متن قابل نمایش تبدیل می‌کند. */
fun <T> LoadState<T>.label(): String = when (this) {
    LoadState.Idle -> "Idle"
    LoadState.Loading -> "Loading"
    is LoadState.Success -> "Success: $data"
    is LoadState.Error -> "Error: $message"
}
