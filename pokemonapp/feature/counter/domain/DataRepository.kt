package com.kotlin.pokemonapp.feature.counter.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class DataRepository {
    // Suspend function to get a single value
    suspend fun fetchDataOnce(): String {
        delay(1000) // Simulate network or database delay
        return "Single result from fetchDataOnce() random=${Random.nextInt()}"
    }

    // Flow to return a stream of data
    fun fetchDataStream(): Flow<String> = flow {
        var count = 1
        while (true) {
            emit("Seconds elapsed: $count")
            count++
            delay(1000) // Emit data every 1 second
        }
    }
}
