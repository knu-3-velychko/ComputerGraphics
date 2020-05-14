class CircularList<T> {
    private var root: CircularListNode<T>? = null
    private var tail: CircularListNode<T>? = null
    private var size = 0
    fun size(): Int {
        return size
    }

    fun insertToEnd(data: T): CircularListNode<T> {
        size++
        val node: CircularListNode<T> = CircularListNode(data)
        if (root == null) {
            root = node
            root?.next = root
            root?.prev = root
            tail = root
            return node
        }
        tail?.next = node
        node.prev = tail
        node.next = root
        tail = node
        root?.prev = tail
        return node
    }

    fun getTail(): CircularListNode<T>? {
        return tail
    }

    fun getRoot(): CircularListNode<T>? {
        return root
    }

    fun insertAfter(node: CircularListNode<T>, data: T): CircularListNode<T> {
        val newNode: CircularListNode<T> = CircularListNode(data)
        newNode.next = node.next
        newNode.prev = node
        node.next = newNode
        newNode.next?.prev = newNode
        if (node === tail) {
            tail = newNode
            root?.prev = tail
        }
        return newNode
    }

    fun splitNext(nodeA: CircularListNode<T>, nodeB: CircularListNode<T>) {
        nodeA.next = nodeB
        nodeB.prev = nodeA
        root = nodeA
        tail = nodeA.prev
    }

    fun splitPrev(nodeA: CircularListNode<T>, nodeB: CircularListNode<T>) {
        nodeA.prev = nodeB
        nodeB.next = nodeA
        root = nodeA
        tail = nodeA.prev
    }

    fun remove(iter: CircularListNode<T>?) {
        if (iter == null) return
        if (root == null) return
        size--
        if (root === tail) {
            if (iter !== root) return
            root = null
            tail = null
            return
        }
        if (iter === root) {
            root = root?.next
            root?.prev = tail
        } else if (iter === tail) {
            tail = tail?.prev
            root?.prev = tail
        } else {
            iter.next?.prev = iter.prev
            iter.prev?.next = iter.next
        }
    }
}