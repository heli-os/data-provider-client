package kr.dataportal.client.core.internal.scheduler

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kr.dataportal.client.core.internal.extensions.tryClose
import org.junit.jupiter.api.Test
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

/**
 * @author Heli
 * Created on 2023. 02. 06
 */
internal class SchedulersTest {

    @Test
    fun `schedule`() {
        val executorService = mockk<ScheduledExecutorService>(relaxed = true)
        val future = mockk<ScheduledFuture<*>>(relaxed = true)
        every { executorService.schedule(any(), any(), any()) } returns future

        val scheduler = Schedulers.executor(executorService)
        val task = mockk<() -> Unit>(relaxed = true)
        val job = scheduler.schedule(50, TimeUnit.MILLISECONDS, task)

        job.cancel()

        val realTask = slot<Runnable>()
        verify(exactly = 1) {
            executorService.schedule(capture(realTask), 50, TimeUnit.MILLISECONDS)
        }

        realTask.captured.run()
        verify(exactly = 1) { task.invoke() }
        verify(exactly = 1) { future.cancel(false) }
    }

    @Test
    fun `schedulePeriodically`() {
        val executor = mockk<ScheduledExecutorService>(relaxed = true)
        val future = mockk<ScheduledFuture<*>>(relaxed = true)
        every { executor.scheduleAtFixedRate(any(), any(), any(), any()) } returns future

        val scheduler = Schedulers.executor(executor)
        val task = mockk<() -> Unit>(relaxed = true)
        val job = scheduler.schedulePeriodically(42, 320, TimeUnit.MILLISECONDS, task)

        job.cancel()

        val realTask = slot<Runnable>()
        verify(exactly = 1) { executor.scheduleAtFixedRate(capture(realTask), 42, 320, TimeUnit.MILLISECONDS) }

        realTask.captured.run()
        verify(exactly = 1) { task.invoke() }

        verify(exactly = 1) { future.cancel(false) }
    }

    @Test
    fun `close`() {
        val executor = mockk<ScheduledExecutorService>(relaxed = true)
        val scheduler = Schedulers.executor(executor)
        scheduler.tryClose()
        verify(exactly = 1) {
            executor.shutdownNow()
        }
    }
}
