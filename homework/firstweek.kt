package com.example.calcproject

interface AbstractOperation {
    fun operate(a: Int, b: Int) : Double
}

class AddOperation : AbstractOperation {
    override fun operate(a: Int, b: Int): Double {
        return (a + b).toDouble()
    }
}

class SubtractOperation : AbstractOperation {
    override fun operate(a: Int, b: Int): Double {
        return (a - b).toDouble()
    }
}

class MultiplyOperation : AbstractOperation {
    override fun operate(a: Int, b: Int): Double {
        return (a * b).toDouble()
    }
}

class DivideOperation : AbstractOperation {
    override fun operate(a: Int, b: Int): Double {
        return a.toDouble() / b.toDouble()
    }
}

class Calculator(val op: AbstractOperation) {
    fun operate(a: Int, b: Int) : Double {
        return op.operate(a, b)
    }
}

fun main() {
    val nums : List<Int> = readLine()!!.split(" ").map { it.toInt() }
    val addCalc = Calculator(AddOperation())
    val subCalc = Calculator(SubtractOperation())
    val mulCalc = Calculator(MultiplyOperation())
    val divCalc = Calculator(DivideOperation())

    if (nums.size != 2) {
        println("unexpected input")
        return
    }

    println("a + b : ${addCalc.operate(nums[0], nums[1])}")
    println("a - b : ${subCalc.operate(nums[0], nums[1])}")
    println("a * b : ${mulCalc.operate(nums[0], nums[1])}")
    println("a / b : ${divCalc.operate(nums[0], nums[1])}")
}
