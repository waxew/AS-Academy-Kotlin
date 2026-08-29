package academy.kotlin.solutions

interface UserRepository { fun count(): Int }
interface Clock { fun nowMillis(): Long }

// Dependencyها به جای singleton/global state از constructor وارد می‌شوند.
class DashboardService(
    private val users: UserRepository,
    private val clock: Clock,
) {
    fun snapshot(): String = "users=${users.count()},at=${clock.nowMillis()}"
}
