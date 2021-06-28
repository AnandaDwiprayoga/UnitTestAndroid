package com.pasukanlangit.id.unittesttdd.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.pasukanlangit.id.unittesttdd.utils.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
class ShoppingDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecuteRule = InstantTaskExecutorRule()

    @Inject
    lateinit var database: MyDatabase

    private lateinit var shoppingDao : ShoppingDao

    @Before
    @Named("test_db")
    fun setUp(){
        hiltRule.inject()
        shoppingDao = database.shoppingDao()
    }

    @After
    fun tearDown(){
       database.close()
    }

    @Test
    fun insertShoppingTest() = runBlockingTest {
        val shoppingItem = ShoppingItem("",1,2000f,"", 1)
        shoppingDao.insertShoppingItem(shoppingItem)

        val allShoppingItems = shoppingDao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("",1,2000f,"",1)
        shoppingDao.insertShoppingItem(shoppingItem)
        shoppingDao.deleteShoppingItem(shoppingItem)

        val allShoppingItems = shoppingDao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun observeTotalPriceSum() = runBlockingTest {
        val shoppingItem1 = ShoppingItem("",2,2000f,"",1)
        val shoppingItem2 = ShoppingItem("",4,2000f,"",2)
        val shoppingItem3 = ShoppingItem("",3,2000f,"",3)

        shoppingDao.insertShoppingItem(shoppingItem1)
        shoppingDao.insertShoppingItem(shoppingItem2)
        shoppingDao.insertShoppingItem(shoppingItem3)

        val totalPriceSum = shoppingDao.observeTotalPrice().getOrAwaitValue()
        assertThat(totalPriceSum).isEqualTo(calculatePrice(shoppingItem1) + calculatePrice(shoppingItem2) + calculatePrice(shoppingItem3))

    }

    private fun calculatePrice(shoppingItem: ShoppingItem): Float = shoppingItem.amount * shoppingItem.price


}