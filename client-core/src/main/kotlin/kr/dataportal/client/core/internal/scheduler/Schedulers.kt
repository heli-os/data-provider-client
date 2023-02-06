package kr.dataportal.client.core.internal.scheduler

import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

/**
 * @author Heli
 * Created on 2023. 02. 04
 */
object Schedulers {

    fun executor(executorService: ScheduledExecutorService): Scheduler {
        return ExecutorScheduler(executorService)
    }

    private class ExecutorScheduler(
        private val executorService: ScheduledExecutorService
    ) : Scheduler, AutoCloseable {
        override fun schedule(delay: Long, unit: TimeUnit, task: () -> Unit): ScheduledJob {
            return Job(executorService.schedule(task, delay, unit))
        }

        override fun schedulePeriodically(delay: Long, period: Long, unit: TimeUnit, task: () -> Unit): ScheduledJob {
            return Job(executorService.scheduleAtFixedRate(task, delay, period, unit))
        }

        override fun close() {
            executorService.shutdownNow()
        }

        private class Job(
            private val real: ScheduledFuture<*>
        ) : ScheduledJob {
            override fun cancel() {
                real.cancel(false)
            }
        }
    }
}
