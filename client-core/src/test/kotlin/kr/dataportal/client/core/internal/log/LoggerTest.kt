package kr.dataportal.client.core.internal.log

import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA

/**
 * @author Heli
 * Created on 2023. 02. 06
 */
internal class LoggerTest {

    @Test
    fun `기본적으로 세팅되어 있는 Logger는 NoOpLogger 이다`() {
        val logger = Logger<LoggerTest>()
        expectThat(logger).isA<NoOpLogger>()
        expectThat(Logger.factory.getLogger("LoggerTestName")).isA<NoOpLogger>()
        expectThat(Logger.factory.getLogger(LoggerTest::class.java)).isA<NoOpLogger>()
    }

    @Test
    fun `Factory를 이용해 Logger를 가져올 수 있다`() {
        val sut = spyk(Logger.Factory { NoOpLogger })
        sut.getLogger(LoggerTest::class.java)
        verify(exactly = 1) {
            sut.getLogger(any<String>())
        }
    }
}
