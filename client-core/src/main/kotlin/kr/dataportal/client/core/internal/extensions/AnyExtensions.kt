package kr.dataportal.client.core.internal.extensions

import kr.dataportal.client.core.internal.DataProviderInternalClient
import kr.dataportal.client.core.internal.log.Logger

/**
 * @author Heli
 * Created on 2023. 02. 04
 */
private val log = Logger<DataProviderInternalClient>()

fun Any.tryClose() {
    if (this is AutoCloseable) {
        runCatching {
            close()
        }.onFailure {
            log.warn { "Unexpected exception while trying to close $this: $it" }
        }
    }
}

inline fun <reified E : Enum<E>> enumValueOfOrNull(name: String): E? {
    return enumValues<E>().find { it.name.lowercase() == name.lowercase() }
}
