class ChainsMethod(val graph: Graph) {
    val chains by lazy {
        val arrChains = mutableListOf<MutableList<GraphNode>>()
        val edgesOut = edgeWeight()

        for (i in edgesOut.last().indices) {
            createChain(arrChains, 0, i)
        }

        createChainsRecursive(arrChains, edgesOut, graph.nodes.size - 1, 0)

        arrChains
    }



    private fun edgeWeight(): Array<MutableList<Int>> {
        val edgesIn = Array<MutableList<Int>>(graph.nodes.size) { mutableListOf() }
        val edgesOut = Array<MutableList<Int>>(graph.nodes.size) { mutableListOf() }

        edgesInOut(edgesIn, edgesOut)

        upIteration(edgesIn, edgesOut)
        downIteration(edgesIn, edgesOut)

        return edgesOut
    }

    private fun edgesInOut(edgesIn: Array<MutableList<Int>>, edgesOut: Array<MutableList<Int>>) {
        for (i in graph.edges.indices) {
            if (graph.edges[i].from.y <= graph.edges[i].to.y) {
                edgesIn[getIndex(graph.edges[i].from)].add(i)
                edgesOut[getIndex(graph.edges[i].to)].add(i)
            } else {
                edgesIn[getIndex(graph.edges[i].to)].add(i)
                edgesOut[getIndex(graph.edges[i].from)].add(i)

                graph.edges[i].swap()
            }
            graph.edges[i].weight = 1
        }
    }

    private fun upIteration(edgesIn: Array<MutableList<Int>>, edgesOut: Array<MutableList<Int>>) {
        for (i in 1 until graph.nodes.size - 1) {
            val weightSumIn = weightSum(edgesIn[i])
            val weightSumOut = edgesOut[i].size

            val leftest = leftestOut(edgesOut[i])
            if (leftest == -1) {
                continue
            }

            if (weightSumIn > weightSumOut) {
                graph.edges[leftest].weight = weightSumIn - weightSumOut + 1
            }
        }
    }

    private fun downIteration(edgesIn: Array<MutableList<Int>>, edgesOut: Array<MutableList<Int>>) {
        for (i in graph.nodes.size - 2 downTo 1) {
            val weightSumOut = weightSum(edgesOut[i])
            val weightSumIn = weightSum(edgesIn[i])

            val leftest = leftestIn(edgesIn[i])
            if (leftest == -1) {
                continue
            }

            if (weightSumOut > weightSumIn) {
                val weight = graph.edges[leftest].weight
                graph.edges[leftest].weight = weight + weightSumOut - weightSumIn
            }
        }
    }

    private fun weightSum(weight: List<Int>): Int {
        var sum = 0
        for (i in weight) {
            sum += graph.edges[i].weight
        }
        return sum
    }

    private fun leftestOut(list: List<Int>): Int {
        var leftest = -1

        for (i in list) {
            if (leftest == -1 || graph.edges[i].to.x < graph.edges[leftest].to.x) {
                leftest = i
            }
        }

        return leftest
    }

    private fun leftestIn(list: List<Int>): Int {
        var leftest = -1
        for (i in list) {
            if (leftest == -1 || graph.edges[i].from.x < graph.edges[leftest].from.x) {
                leftest = i
            }
        }

        return leftest
    }

    private fun createChain(arrChains: MutableList<MutableList<GraphNode>>, id: Int, shift: Int) {
        arrChains.add(id + shift, mutableListOf())
        for (i in arrChains[id]) {
            if (!arrChains[id + shift].contains(i)) {
                arrChains[id + shift].add(i)
            }
        }
    }

    private fun createChainsRecursive(
        arrChains: MutableList<MutableList<GraphNode>>,
        edgesOut: Array<MutableList<Int>>,
        node: Int,
        id: Int
    ): Int {
        var chainId = id
        val list = edgesLeftToRight(edgesOut[node], node)
        val nodes = graph.nodes
        nodes.sortBy { it.y }

        if (list.isEmpty()) {
            arrChains[chainId].add(nodes[node])
        }

        for (i in list.indices) {
            if (i > 0) {
                chainId++
            }

            arrChains[chainId].add(nodes[node])
            val nextNode =
                if (getIndex(graph.edges[list[i]].from) == node) {
                    getIndex(graph.edges[list[i]].to)
                } else {
                    getIndex(graph.edges[list[i]].from)
                }

            if (edgesOut[nextNode].size > 1) {
                for (j in 1 until edgesOut[nextNode].size) {
                    createChain(arrChains, chainId, j)
                }
            }
            chainId = createChainsRecursive(arrChains, edgesOut, nextNode, chainId)
        }

        return chainId
    }

    private fun edgesLeftToRight(edgesId: MutableList<Int>, node: Int): MutableList<Int> {
        for (i in edgesId) {
            for (j in i + 1 until edgesId.size) {
                if (getSide(
                        graph.edges[edgesId[i]].from,
                        graph.edges[edgesId[i]].to,
                        graph.edges[edgesId[j]].to
                    ) <= 0
                ) {
                    val tmp = edgesId[j]
                    edgesId[j] = edgesId[i]
                    edgesId[i] = tmp
                }
            }
        }

        return edgesId
    }

    private fun getIndex(node: GraphNode): Int {
        val nodes = graph.nodes
        nodes.sortBy { it.y }
        for (i in nodes.indices) {
            if (nodes[i] == node) {
                return i
            }
        }
        return -1
    }

    private fun getSide(a: GraphNode, b: GraphNode, c: GraphNode): Int {
        val distance = getRotation(a, b, c)

        if (distance > 0)
            return 1
        if (distance < 0)
            return -1
        return 0
    }


    private fun getRotation(a: GraphNode, b: GraphNode, c: GraphNode) =
        (c.y - a.y) * (b.x - a.x) - (b.y - a.y) * (c.x - a.x)
}