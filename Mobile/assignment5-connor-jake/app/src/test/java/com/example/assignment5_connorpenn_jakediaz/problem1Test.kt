package com.example.assignment5_connorpenn_jakediaz

import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class problem1Test {
    private val problem1 = problem1()
    @Test
    fun testPushOutOfSpace() {
        fillArray()
        assertThrows<Exception> {
            problem1.push(0,1)
        }
        assertThrows<Exception>{
            problem1.push(1,1)
        }
        assertThrows<Exception>{
            problem1.push(2,1)
        }

    }
    @Test
    fun testPush() {
        problem1.push(0,1)
        assert(!problem1.isEmpty(0))
    }
    @Test
    fun emptyPopTest() {
        assertThrows<Exception> {
            problem1.pop(0)
        }
        assertThrows<Exception>{
            problem1.pop(1)
        }
        assertThrows<Exception>{
            problem1.pop(2)
        }
    }
    @Test
    fun popTest() {
        problem1.push(0,1)
        assertEquals(problem1.pop(0), 1)
        problem1.push(1,1)
        assertEquals(problem1.pop(1),1)
        problem1.push(2,1)
        assertEquals(problem1.pop(2), 1)
    }
    @Test
    fun isEmptyTest() {
        fillArray()
        destroyArray()
        assert(problem1.isEmpty(0))
    }
    @Test
    fun testPeek() {
        problem1.push(0, 100)
        assertEquals(problem1.peek(0),100)
    }
    private fun fillArray() {

        var stackSize = 0
        while (stackSize < 300) {
            problem1.push(0, 1)
            problem1.push(1, 2)
            problem1.push(2,3)
            stackSize += 3
        }
    }
    @Test
    fun testTopOfStack() {
        fillArray()
        assertEquals(problem1.stackSize - 1, problem1.absTopOfStack(0))
    }
    @AfterEach
    fun destroyArray() {
        while (!problem1.isEmpty(0)){
            problem1.pop(0)
        }
        while (!problem1.isEmpty(1)){
            problem1.pop(1)
        }
        while (!problem1.isEmpty(2)){
            problem1.pop(2)
        }
    }
}