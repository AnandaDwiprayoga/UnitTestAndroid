package com.pasukanlangit.id.unittesttdd.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.pasukanlangit.id.unittesttdd.data.remote.model.DogResponse
import com.pasukanlangit.id.unittesttdd.repositories.FakeDogRepository
import com.pasukanlangit.id.unittesttdd.utils.MainCoroutineRule
import com.pasukanlangit.id.unittesttdd.utils.Status
import com.pasukanlangit.id.unittesttdd.utils.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

import org.junit.Test

@ExperimentalCoroutinesApi
class DogViewModelTest {

    private lateinit var dogViewModel: DogViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule =  MainCoroutineRule()

    @Before
    fun setUp() {
        dogViewModel = DogViewModel(FakeDogRepository())
    }

    @Test
    fun `insert dog item with empty field, returns error`(){
        dogViewModel.insertDogItem(DogResponse(data = emptyList(), status = ""))

        val insertStatus = dogViewModel.insertDogStatus.getOrAwaitValueTest()

        assertThat(insertStatus.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `should return success when property not empty`(){
        dogViewModel.insertDogItem(DogResponse(data = listOf("url1","url2"), status = "success"))

        val insertStatus = dogViewModel.insertDogStatus.getOrAwaitValueTest()

        assertThat(insertStatus.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }
}