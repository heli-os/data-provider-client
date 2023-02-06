package kr.dataportal.client.core.internal.threads

import java.util.concurrent.*

/**
 * @author Heli
 * Created on 2023. 02. 04
 */
object PoolingExecutors {

    private const val KEEP_ALIVE_TIME_MILLIS: Long = 0L

    fun newThreadPool(
        poolSize: Int,
        workerQueueCapacity: Int,
        threadFactory: ThreadFactory
    ): ExecutorService {
        return ThreadPoolExecutor(
            poolSize, poolSize,
            KEEP_ALIVE_TIME_MILLIS, TimeUnit.MILLISECONDS,
            ArrayBlockingQueue(workerQueueCapacity),
            threadFactory
        )
    }
}
