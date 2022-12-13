class CustomLock(private val parties: Int) {
    private var locked = 0
    private val threads: MutableList<Thread>

    init {
        threads = ArrayList()
    }

    @Synchronized
    fun lock() {
        val thread = Thread.currentThread()
        while (!thread.isInterrupted) {
            check(!threads.contains(thread))
            if (locked < parties) {
                locked += 1
                threads.add(thread)
                break
            } else {
                (this as java.lang.Object).wait()
            }
        }
    }

    @Synchronized
    fun unlock() {
        val thread = Thread.currentThread()
        check(threads.contains(thread))
        threads.remove(thread)
        locked -= 1
        (this as java.lang.Object).notifyAll()
    }
}