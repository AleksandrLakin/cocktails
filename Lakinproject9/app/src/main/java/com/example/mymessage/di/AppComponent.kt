package com.example.mymessage.di

import com.example.mymessage.di.auth.AuthComponent
import com.example.mymessage.di.cocktails.CocktailComponent
import com.example.mymessage.di.details.DetailsComponent
import com.example.mymessage.di.register.RegisterComponent
import com.example.mymessage.di.users.UsersComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun getAuthComponent(): AuthComponent

    fun getRegisterComponent(): RegisterComponent

    fun getUsersComponent(): UsersComponent

    fun getCocktailComponent(): CocktailComponent

    fun getDetailsComponent(): DetailsComponent

}