package com.quiz.client

import com.google.firebase.iid.FirebaseInstanceId
import com.quiz.client.util.countScore
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun countScoreTest(){
        println(countScore("55a"))
        println(countScore("15"))
        assertNotNull(countScore("15"))
        assertNotNull(countScore("25"))
        assertNotNull(countScore("55"))
        assertNotNull(countScore("55a"))
    }

}
