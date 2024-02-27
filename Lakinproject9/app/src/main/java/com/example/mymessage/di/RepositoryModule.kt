package com.example.mymessage.di

import com.example.mymessage.data.auth.AuthRepository
import com.example.mymessage.data.auth.AuthRepositoryImpl
import com.example.mymessage.data.cocktails.CocktailsRepository
import com.example.mymessage.data.cocktails.CocktailsRepositoryImpl
import com.example.mymessage.data.users.UsersRepository
import com.example.mymessage.data.users.UsersRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {

    @Provides
    fun provideRepositoryModule(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideCocktailsRepository(impl: CocktailsRepositoryImpl): CocktailsRepository = impl

}