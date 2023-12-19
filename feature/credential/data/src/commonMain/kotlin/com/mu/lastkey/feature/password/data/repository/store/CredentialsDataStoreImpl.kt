package com.mu.lastkey.feature.password.data.repository.store

import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.core.domain.model.credential.Credential
import org.mobilenativefoundation.store.store5.impl.extensions.fresh
import org.mobilenativefoundation.store.store5.impl.extensions.get

internal class CredentialsDataStoreImpl(
    private val storeProvider: CredentialsStoreProvider
) : CredentialsDataStore {
    override suspend fun getCredentials(
        offset: Int,
        refresh: Boolean,
        pagingKey: String
    ): ResultWrapper<List<Credential>> {
        val key = CredentialsStoreProvider.Key(offset = offset, pagingKey = pagingKey)
        var result = if (refresh) storeProvider.fresh(key) else storeProvider.get(key)
        if (result.isEmpty() && !refresh) result = storeProvider.fresh(key)
        return ResultWrapper.Success(result)
    }
}
