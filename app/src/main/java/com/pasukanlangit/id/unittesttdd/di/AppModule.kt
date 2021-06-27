package com.pasukanlangit.id.unittesttdd.di

import android.content.Context
import androidx.room.Room
import com.pasukanlangit.id.unittesttdd.data.local.MyDatabase
import com.pasukanlangit.id.unittesttdd.data.remote.DogApi
import com.pasukanlangit.id.unittesttdd.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, MyDatabase::class.java, Constant.DATABASE_NAME).build()

    @Provides
    fun provideShoppingDao(
        db: MyDatabase
    ) = db.shoppingDao()

    @Provides
    fun provideDogDao(
        db: MyDatabase
    ) = db.dogDao()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constant.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideDogApi(retrofit: Retrofit) = retrofit.create(DogApi::class.java)

}