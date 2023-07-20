fun solution(n: Int): ArrayList<Int> {
    var answer: ArrayList<Int> = ArrayList<Int>()
    var maps = Array(n) {IntArray(n){0}}
    var du = ArrayList<Pair<Int, Int>>().also {
        it.add(Pair(1, 0))
        it.add(Pair(0, 1))
        it.add(Pair(-1, -1))
    }

    var x = 0
    var y = 0
    var state = 0
    var num = 1

    while(true) {
        maps[x][y] = num

        var nextX = x + du[state%3].first
        var nextY = y + du[state%3].second

        if(nextX >= 0 && nextX < n && nextY >=0 && nextY < n && maps[nextX][nextY] == 0) {
            x = nextX
            y = nextY
            num = num + 1
        }
        else {
            state++

            var nextX = x + du[state%3].first
            var nextY = y + du[state%3].second

            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && maps[nextX][nextY] == 0) {
                x = nextX
                y = nextY
                num = num + 1
            } else {
                break
            }
        }
    }

    answer.also {
        for(item in maps) {
            it.addAll(item.toList().filter {it > 0})
        }
    }

    return answer
}
