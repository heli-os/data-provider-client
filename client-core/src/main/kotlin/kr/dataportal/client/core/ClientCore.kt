package kr.dataportal.client.core

import kr.dataportal.client.core.internal.DataProviderInternalClient
import kr.dataportal.client.core.source.SourceFetcher

/**
 * @author Heli
 * Created on 2023. 02. 04
 */
object ClientCore

fun ClientCore.client(
    sourceFetcher: SourceFetcher
): DataProviderInternalClient {
    return DataProviderInternalClient(
        sourceFetcher = sourceFetcher
    )
}
