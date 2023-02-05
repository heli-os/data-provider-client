package kr.dataportal.client.core.extensions

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.dataportal.client.core.internal.extensions.enumValueOfOrNull
import kr.dataportal.client.core.internal.extensions.tryClose
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import strikt.api.expectThat
import strikt.assertions.isEqualTo

/**
 * @author Heli
 * Created on 2023. 02. 06
 */
internal class AnyExtensionsTest {
    @Test
    fun `AutoCloseable 인경우 close 를 호출한다`() {
        val sut = mockk<AutoCloseable>(relaxUnitFun = true)
        sut.tryClose()
        verify(exactly = 1) {
            sut.close()
        }
    }

    @Test
    fun `AutoCloseable 을 닫다가 예외가 발생해도 무시한다`() {
        val sut = mockk<AutoCloseable>(relaxUnitFun = true) {
            every { close() } throws Exception()
        }
        sut.tryClose()
        verify(exactly = 1) {
            sut.close()
        }
    }

    @Test
    fun `enumValueOfOrNull - 대문자`() {
        val stubA = enumValueOfOrNull<TestStub>("A") ?: fail("must not be null.")
        val stubB = enumValueOfOrNull<TestStub>("B") ?: fail("must not be null.")
        val stubC = enumValueOfOrNull<TestStub>("C") ?: fail("must not be null.")

        expectThat(stubA) isEqualTo TestStub.A
        expectThat(stubB) isEqualTo TestStub.B
        expectThat(stubC) isEqualTo TestStub.C
    }

    @Test
    fun `enumValueOfOrNull - 소문자`() {
        val stubA = enumValueOfOrNull<TestStub>("a") ?: fail("must not be null.")
        val stubB = enumValueOfOrNull<TestStub>("b") ?: fail("must not be null.")
        val stubC = enumValueOfOrNull<TestStub>("c") ?: fail("must not be null.")

        expectThat(stubA) isEqualTo TestStub.A
        expectThat(stubB) isEqualTo TestStub.B
        expectThat(stubC) isEqualTo TestStub.C
    }

    private enum class TestStub {
        A, B, C;
    }
}
