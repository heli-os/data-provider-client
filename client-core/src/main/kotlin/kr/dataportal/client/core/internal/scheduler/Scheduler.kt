package kr.dataportal.client.core.internal.scheduler

import java.util.concurrent.TimeUnit

/**
 * @author Heli
 * Created on 2023. 02. 04
 */
interface Scheduler {
    fun schedule(delay: Long, unit: TimeUnit, task: () -> Unit): ScheduledJob
    fun schedulePeriodically(delay: Long, period: Long, unit: TimeUnit, task: () -> Unit): ScheduledJob
}
