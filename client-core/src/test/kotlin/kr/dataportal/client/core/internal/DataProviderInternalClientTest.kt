package kr.dataportal.client.core.internal

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kr.dataportal.client.common.Address
import kr.dataportal.client.common.Company
import kr.dataportal.client.common.Geo
import kr.dataportal.client.common.User
import kr.dataportal.client.core.model.source.SourceUser
import kr.dataportal.client.core.source.Source
import kr.dataportal.client.core.source.SourceFetcher
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

/**
 * @author Heli
 * Created on 2023. 02. 06
 */
@ExtendWith(MockKExtension::class)
internal class DataProviderInternalClientTest {

    @MockK
    private lateinit var sourceFetcher: SourceFetcher

    @InjectMockKs
    private lateinit var sut: DataProviderInternalClient

    @Test
    fun `Source 데이터를 가져오지 못하면 None User 를 반환한다`() {
        every { sourceFetcher.fetch() } returns null

        val actual = sut.user(42L)
        expectThat(actual).isA<User.None>()
    }

    @Test
    fun `userId에 해당하는 User 가 없으면 None User 를 반환한다`() {
        val source = mockk<Source> {
            every { getUserOrNull(any()) } returns null
        }

        every { sourceFetcher.fetch() } returns source

        val actual = sut.user(42L)
        expectThat(actual).isA<User.None>()
    }

    @Test
    fun `Value User 를 정상적으로 반환한다`() {
        val sourceUser = SourceUser(
            id = 42L,
            name = "name",
            username = "username",
            email = "email",
            address = Address("street", "suite", "city", "zipcode", Geo("lat", "lng")),
            phone = "phone",
            website = "website",
            company = Company("name", "catchPhrase", "bs")
        )
        val source = mockk<Source> {
            every { getUserOrNull(any()) } returns sourceUser
        }

        every { sourceFetcher.fetch() } returns source

        val actual = sut.user(42L)
        expectThat(actual)
            .isA<User.Value>()
            .get {
                expectThat(id) isEqualTo sourceUser.id
                expectThat(name) isEqualTo sourceUser.name
                expectThat(username) isEqualTo sourceUser.username
                expectThat(email) isEqualTo sourceUser.email
                expectThat(phone) isEqualTo sourceUser.phone
                expectThat(website) isEqualTo sourceUser.website
                expectThat(address) isEqualTo sourceUser.address
                expectThat(company) isEqualTo sourceUser.company
            }
    }
}
