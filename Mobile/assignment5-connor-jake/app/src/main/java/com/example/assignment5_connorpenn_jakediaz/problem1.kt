package com.example.assignment5_connorpenn_jakediaz

class problem1 {
    var stackSize = 100
    var buffer: Array<Int?> = arrayOfNulls(stackSize * 3)
    var stackPointer = arrayOf(-1, -1, -1)

    fun push(stackNum: Int, value: Int) {
        if (stackPointer[stackNum] + 1 >= stackSize) {
            throw Exception("Out of space.")
        }
        stackPointer[stackNum]++
        buffer[absTopOfStack(stackNum)] = value
    }

    fun pop(stackNum: Int): Int? {
        if (stackPointer[stackNum] == -1) {
            throw Exception("Trying to pop an empty stack.")
        }
        val value = buffer[absTopOfStack(stackNum)]
        buffer[absTopOfStack(stackNum)] = 0
        stackPointer[stackNum]--
        return value
    }

    fun peek(stackNum: Int): Int? {
        val index = absTopOfStack(stackNum);
        return buffer[stackNum]
    }

    fun isEmpty(stackNum: Int): Boolean {
        return stackPointer[stackNum] == 1
    }

    fun absTopOfStack(stackNum: Int): Int {
        return stackNum * stackSize + stackPointer[stackNum]
    }
}