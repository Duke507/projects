package com.example.assignment5_connorpenn_jakediaz

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class problem2Test{
    val problem2 = problem2()
    @Test
    fun testSearchEmpty() {
        val emptyArray = Array(10) {_->""}
        assertEquals(problem2.search(emptyArray, "test"), -1)
    }
    @Test
    fun testSearch(){
        val testArray = arrayOf("dog","cat","dog","dog")
        assertEquals(problem2.search(testArray, "cat"), 1)
    }
    @Test
    fun testWordNotFound(){
        val testArray = arrayOf("I","just","lost","my","dog")
        assertEquals(problem2.search(testArray, "cat"), -1)
    }
    @Test
    fun testEmptyString(){
        val testArray = arrayOf("i")
        assertEquals(problem2.search(testArray, ""), -1)
    }
    @Test
    fun testFirstIndex(){
        val testArray = arrayOf("dog","cat","cat","cat","cat")
        assertEquals(problem2.search(testArray, "dog"),0)
    } //explain that this is an error because of how the search algorithm behaves
    @Test
    fun testLastIndex(){
        val testArray = arrayOf("cat","cat","cat","dog")
        assertEquals(problem2.search(testArray, "dog"), 3)
    }
    @Test
    fun testSingleCellArray(){
        val testArray = arrayOf("i")
        assertEquals(problem2.search(testArray, "i"),0)
    }
    @Test
    fun testIncorrectIndicies(){
        val testArray = arrayOf("1","2","3","4")
        assertEquals(problem2.searchR(testArray,"4", 5, 1),-1)
    }
    @Test
    fun testMiddleCorrect(){
        val testArray = arrayOf("cat", "dog", "cat")
        assertEquals(problem2.search(testArray, "dog"), 1)
    }
}