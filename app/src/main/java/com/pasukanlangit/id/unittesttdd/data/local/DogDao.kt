package com.pasukanlangit.id.unittesttdd.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pasukanlangit.id.unittesttdd.data.remote.model.DogResponse

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dogResponse: DogResponse)

    @Query("SELECT * FROM dog")
    fun getAllDog(): LiveData<List<DogResponse>>

    @Delete
    suspend fun deleteDog(dogResponse: DogResponse)
}