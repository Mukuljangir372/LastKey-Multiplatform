package com.mu.lastkey.feature.login.data.di

import com.mu.lastkey.core.data.local.AuthUserLocalDataSource
import com.mu.lastkey.core.data.mapper.AuthUserMapper
import com.mu.lastkey.core.domain.model.coroutine.AppCoroutineDispatchers
import com.mu.lastkey.core.network.realm.RealmClient
import com.mu.lastkey.feature.login.data.network.LoginNetworkDataSource
import com.mu.lastkey.feature.login.data.network.LoginNetworkDataSourceImpl
import com.mu.lastkey.feature.login.data.network.api.LoginApi
import com.mu.lastkey.feature.login.data.network.api.LoginApiImpl
import com.mu.lastkey.feature.login.data.network.api.UserApi
import com.mu.lastkey.feature.login.data.network.api.UserApiImpl
import com.mu.lastkey.feature.login.data.repository.LoginRepositoryImpl
import com.mu.lastkey.feature.login.data.usecase.SignInUsecaseImpl
import com.mu.lastkey.feature.login.data.usecase.SignUpUsecaseImpl
import com.mu.lastkey.feature.login.domain.repository.LoginRepository
import com.mu.lastkey.feature.login.domain.usecase.SignInUsecase
import com.mu.lastkey.feature.login.domain.usecase.SignUpUsecase
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

fun getLoginDataModule(): Module {
    return module {
        single { provideLoginApi(get()) }
        single { provideUserApi(get()) }
        single { provideLoginNetworkDataSource(get(), get(), get<AppCoroutineDispatchers>().io) }
        single { provideLoginRepository(get(), get(), get(), get<AppCoroutineDispatchers>().default) }
        single { provideSignInUsecase(get(), get<AppCoroutineDispatchers>().default) }
        single { provideSignUpUsecase(get(), get<AppCoroutineDispatchers>().default) }
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

private fun provideLoginRepository(
    loginNetworkDataSource: LoginNetworkDataSource,
    authUserLocalDataSource: AuthUserLocalDataSource,
    authUserMapper: AuthUserMapper,
    coroutineDispatcher: CoroutineDispatcher
): LoginRepository {
    return LoginRepositoryImpl(
        networkSource = loginNetworkDataSource,
        authUserLocalDataSource = authUserLocalDataSource,
        authUserMapper = authUserMapper,
        coroutineDispatcher = coroutineDispatcher
    )
}

private fun provideSignInUsecase(
    loginRepository: LoginRepository,
    coroutineDispatcher: CoroutineDispatcher
): SignInUsecase {
    return SignInUsecaseImpl(
        repository = loginRepository,
        coroutineDispatcher = coroutineDispatcher
    )
}

private fun provideSignUpUsecase(
    loginRepository: LoginRepository,
    coroutineDispatcher: CoroutineDispatcher
): SignUpUsecase {
    return SignUpUsecaseImpl(
        repository = loginRepository,
        coroutineDispatcher = coroutineDispatcher
    )
}
