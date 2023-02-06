package kr.dataportal.client.core.source

import kr.dataportal.client.core.model.source.SourceUser

/**
 * @author Heli
 * Created on 2023. 02. 06
 */
interface Source {
    fun getUserOrNull(userId: Long): SourceUser?
}
