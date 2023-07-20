import java.util.*

data class Node(val dist : Int, val v : Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int = dist - other.dist
}

fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
    var answer = 0
    val pq = PriorityQueue<Node>()
    val dist = IntArray(N + 1){123456789}
    val graph = ArrayList<ArrayList<Pair<Int, Int>>>().also {
        for(i in 0..N) {
            it.add(ArrayList<Pair<Int, Int>>())
        }
    }.also {
        for(item in road) {
            it[item[0]].add(Pair<Int, Int>(item[1], item[2]))
            it[item[1]].add(Pair<Int, Int>(item[0], item[2]))
        }
    }

    dist[1] = 0
    pq.add(Node(0, 1))

    while(pq.isNotEmpty()) {
        val vt = pq.poll()

        if (dist[vt.v] < vt.dist) {
            continue
        }

        for(c in graph[vt.v]) {
            if (dist[c.first] > dist[vt.v] + c.second) {
                dist[c.first] = dist[vt.v] + c.second
                pq.add(Node(dist[c.first], c.first))
            }
        }
    }

    answer = dist.filter {it <= k}.size

    return answer
}
