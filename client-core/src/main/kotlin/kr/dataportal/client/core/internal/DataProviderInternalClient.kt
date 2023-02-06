package kr.dataportal.client.core.internal

import kr.dataportal.client.common.User
import kr.dataportal.client.core.internal.extensions.toUser
import kr.dataportal.client.core.internal.extensions.tryClose
import kr.dataportal.client.core.source.SourceFetcher

/**
 * @author Heli
 * Created on 2023. 02. 04
 */
class DataProviderInternalClient internal constructor(
    private val sourceFetcher: SourceFetcher
) : AutoCloseable {

    fun user(userId: Long): User {
        val source = sourceFetcher.fetch() ?: return User.None
        val sourceUser = source.getUserOrNull(userId = userId) ?: return User.None

        return sourceUser.toUser()
    }

    override fun close() {
        sourceFetcher.tryClose()
    }
}
