package kr.dataportal.client.core.model.source

import kr.dataportal.client.common.Address
import kr.dataportal.client.common.Company

/**
 * @author Heli
 * Created on 2023. 02. 06
 */
data class SourceUser(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)
