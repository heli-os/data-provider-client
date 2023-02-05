package kr.dataportal.client.common

/**
 * @author Heli
 * Created on 2023. 02. 06
 */
// TODO: 추후 client-common 모듈로 분리 필요
sealed class User {

    data class Value internal constructor(
        val id: Long,
        val name: String,
        val username: String,
        val email: String,
        val address: Address,
        val phone: String,
        val website: String,
        val company: Company
    ) : User()

    object None : User()

    companion object {

        @JvmStatic
        fun of(
            id: Long,
            name: String,
            username: String,
            email: String,
            address: Address,
            phone: String,
            website: String,
            company: Company
        ): Value = Value(
            id = id,
            name = name,
            username = username,
            email = email,
            phone = phone,
            website = website,
            address = address.copy(),
            company = company.copy()
        )
    }
}
