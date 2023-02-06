package kr.dataportal.client.core.internal.log

/**
 * @author Heli
 * Created on 2023. 02. 04
 */
internal object NoOpLogger : Logger {
    override fun debug(message: () -> String) {}
    override fun info(message: () -> String) {}
    override fun warn(message: () -> String) {}
    override fun error(message: () -> String) {}
    override fun error(throwable: Throwable, message: () -> String) {}

    object Factory : Logger.Factory {
        override fun getLogger(name: String): Logger = NoOpLogger
    }
}
