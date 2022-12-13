import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.AtomicBoolean

class CustomThreadPool(threadsCount: Int) {
    private val queue: BlockingQueue<Runnable>
    private val finished: AtomicBoolean

    init {
        queue = LinkedBlockingQueue()
        finished = AtomicBoolean(false)
        for (i in 0 until threadsCount) {
            val worker = WorkerThread()
            worker.name = "Worker $i"
            worker.start()
        }
    }

    fun execute(runnableTask: Runnable) {
        if (!finished.get()) {
            queue.put(runnableTask)
            synchronized(this) { (this as java.lang.Object).notifyAll() }
        }
    }

    fun shutdown() {
        finished.set(true)
    }

    private inner class WorkerThread : Thread() {
        override fun run() {
            while (!finished.get()) {
                var runnableTask: Runnable
                while (queue.poll().also { runnableTask = it } != null) {
                    runnableTask.run()
                }
            }
        }
    }
}