package com.pasukanlangit.id.unittesttdd.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "dog")
data class DogResponse(

	@PrimaryKey(autoGenerate = true)
	val id: Int,

	@field:SerializedName("message")
	val data: List<String>,

	@field:SerializedName("status")
	val status: String
)
