class CustomPhaser(private var parties: Int) {
    private var awaiting: Int
    var phase: Int
    private set

    init {
        awaiting = parties
        phase = 0
    }

    @Synchronized
    fun register() {
        parties += 1
        awaiting += 1
    }

    private fun reset() {
        awaiting = parties
        phase += 1
        (this as java.lang.Object).notifyAll()
    }

    @Synchronized
    fun advance() {
        awaiting -= 1
        while (awaiting > 0) {
            (this as java.lang.Object).wait()
        }
        if (awaiting == 0) {
            reset()
        }
    }

    @Synchronized
    fun deregister() {
        awaiting -= 1
        parties -= 1
        if (awaiting == 0) {
            reset()
        }
    }
}