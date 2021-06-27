package com.pasukanlangit.id.unittesttdd.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.pasukanlangit.id.unittesttdd.data.remote.model.DogResponse

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dogResponse: DogResponse)

    @Delete
    suspend fun deleteDog(dogResponse: DogResponse)
}