fun solution(numbers: IntArray): String {
    var answer = ""
    var arr: ArrayList<ArrayList<Any>> = ArrayList<ArrayList<Any>>()

    for(i in 0 until numbers.size) {
        arr.add(ArrayList<Any>().also{
            var tmp: Int = 12 / numbers[i].toString().length
            var txt = StringBuilder().run {
                for(j in 0 until tmp) {
                    append(numbers[i].toString())
                }
                toString()
            }
            it.add(txt)
            it.add(i)
        })
    }

    answer = arr.sortedWith(compareBy({it[0] as String})).reversed().run {
        var len = this.size
        var tmp = this

        StringBuilder().run {
            for(i in 0 until len) {
                append(numbers[tmp[i][1] as Int].toString())
            }
            toString()
        }
    }
    
    return if(answer[0] == '0') "0" else answer
}
