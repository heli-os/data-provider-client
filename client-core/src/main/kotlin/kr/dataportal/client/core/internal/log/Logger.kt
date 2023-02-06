package kr.dataportal.client.core.internal.log

/**
 * @author Heli
 * Created on 2023. 02. 04
 */
interface Logger {

    fun debug(message: () -> String)
    fun info(message: () -> String)
    fun warn(message: () -> String)
    fun error(message: () -> String)
    fun error(throwable: Throwable, message: () -> String)

    fun interface Factory {
        fun getLogger(name: String): Logger

        fun getLogger(clazz: Class<*>): Logger = getLogger(clazz.name)
    }

    companion object {
        var factory: Factory = NoOpLogger.Factory
        inline operator fun <reified T> invoke(): Logger = factory.getLogger(T::class.java)
    }
}
