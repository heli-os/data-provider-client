package kr.dataportal.client.core.source

/**
 * @author Heli
 * Created on 2023. 02. 06
 */
interface SourceFetcher {
    fun fetch(): Source?
}
