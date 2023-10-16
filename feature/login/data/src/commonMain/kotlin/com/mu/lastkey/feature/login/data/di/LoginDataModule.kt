package com.mu.lastkey.feature.login.data.di

import com.mu.lastkey.core.domain.model.coroutine.AppCoroutineDispatchers
import com.mu.lastkey.core.network.realm.RealmClient
import com.mu.lastkey.feature.login.data.network.LoginNetworkDataSource
import com.mu.lastkey.feature.login.data.network.LoginNetworkDataSourceImpl
import com.mu.lastkey.feature.login.data.network.api.LoginApi
import com.mu.lastkey.feature.login.data.network.api.LoginApiImpl
import com.mu.lastkey.feature.login.data.network.api.UserApi
import com.mu.lastkey.feature.login.data.network.api.UserApiImpl
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

fun getLoginDataModule(): Module {
    return module {
        single { provideLoginApi(get()) }
        single { provideUserApi(get()) }
        single { provideLoginNetworkDataSource(get(), get(), get<AppCoroutineDispatchers>().io) }
    }
}

private fun provideLoginApi(realmClient: RealmClient): LoginApi {
    return LoginApiImpl(realmClient = realmClient)
}

private fun provideUserApi(realmClient: RealmClient): UserApi {
    return UserApiImpl(realmClient)
}

private fun provideLoginNetworkDataSource(
    loginApi: LoginApi,
    userApi: UserApi,
    coroutineDispatcher: CoroutineDispatcher
): LoginNetworkDataSource {
    return LoginNetworkDataSourceImpl(
        loginApi = loginApi,
        userApi = userApi,
        coroutineDispatcher = coroutineDispatcher
    )
}
