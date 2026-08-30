// Canonical solution sketch for kt-ex-adv-003.
// The course application may hide solution files until the learner completes the exercise.

import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.advanceTimeBy
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RetryUseCase(private val load: suspend () -> String) {
    suspend fun execute(maxAttempts: Int = 3): String {
        require(maxAttempts > 0)
        var last: Throwable? = null
        repeat(maxAttempts) { attempt ->
            try {
                return withTimeout(1_000) { load() }
            } catch (error: Throwable) {
                last = error
                if (attempt < maxAttempts - 1) delay(100L * (attempt + 1))
            }
        }
        throw requireNotNull(last)
    }
}

class RetryUseCaseTest {
    @Test
    fun `retries transient failure with virtual time`() = runTest {
        var calls = 0
        val useCase = RetryUseCase {
            calls++
            if (calls < 2) error("temporary")
            "ok"
        }

        val result = useCase.execute()
        advanceTimeBy(100)

        assertEquals("ok", result)
        assertEquals(2, calls)
    }

    @Test
    fun `fails after retry budget is exhausted`() = runTest {
        val useCase = RetryUseCase { error("still failing") }
        assertFailsWith<IllegalStateException> { useCase.execute(maxAttempts = 2) }
    }
}
