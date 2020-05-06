package CircularLinkedList

import CircularListNode

class CircularList<T> {
    private var root: CircularListNode<T>? = null
    private var tail: CircularListNode<T>? = null
    private var size = 0
    fun size(): Int {
        return size
    }

    fun insertToEnd(data: T) {
        size++
        val node: CircularListNode<T> = CircularListNode(data)
        if (root == null) {
            root = node
            root?.next = root
            root?.prev = root
            tail = root
            return
        }
        tail?.next = node
        node.prev = tail
        node.next = root
        tail = node
        root?.prev = tail
    }

    fun getTail(): CircularListNode<T>? {
        return tail
    }

    fun getRoot(): CircularListNode<T>? {
        return root
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
        when {
            iter === root -> {
                root = root?.next
                root?.prev = tail
            }
            iter === tail -> {
                tail = tail?.prev
                root?.prev = tail
            }
            else -> {
                iter.next?.prev = iter.prev
                iter.prev?.next = iter.next
            }
        }
    }
}