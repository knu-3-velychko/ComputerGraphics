class CircularListNode<T> {
    var prev: CircularListNode<T>? = null
    var next: CircularListNode<T>? = null
    var data: T

    constructor(data: T) {
        this.data = data
    }

    constructor(data: T, prev: CircularListNode<T>?, next: CircularListNode<T>?) {
        this.prev = prev
        this.next = next
        this.data = data
    }
}