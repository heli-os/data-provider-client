package kr.dataportal.client.core.internal.log

import io.mockk.Called
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isSameInstanceAs

/**
 * @author Heli
 * Created on 2023. 02. 06
 */
internal class NoOpLoggerTest {

    @Test
    fun `message wasNot Called`() {
        val message = mockk<() -> String>()
        NoOpLogger.info(message)
        NoOpLogger.warn(message)
        NoOpLogger.error(message)
        NoOpLogger.error(IllegalArgumentException(), message)
        verify {
            message wasNot Called
        }
    }

    @Test
    fun `NoOpLogger 를 사용한다`() {
        expectThat(NoOpLogger.Factory.getLogger("firstLoggerName"))
            .isSameInstanceAs(NoOpLogger.Factory.getLogger("secondLoggerName"))
            .isSameInstanceAs(NoOpLogger.Factory.getLogger(NoOpLoggerTest::class.java))
    }
}
