var answer = ArrayList<ArrayList<Int>>()

fun hanoi(n: Int, from: Int, to: Int, aux: Int) {
    if (n == 1) {
        answer.add(ArrayList<Int>().also {
            it.add(from)
            it.add(to)
        })
        
        return
    }

    hanoi(n - 1, from, aux, to)

    answer.add(ArrayList<Int>().also{
        it.add(from)
        it.add(to)})

    hanoi(n - 1, aux, to, from)
}

fun solution(n: Int): ArrayList<ArrayList<Int>> {
    hanoi(n, 1, 3, 2)
    return answer
}
