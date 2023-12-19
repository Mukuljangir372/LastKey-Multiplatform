package com.mu.lastkey.core.data.local

import app.cash.sqldelight.db.SqlDriver
import com.mu.lastkey.core.data.AppDatabase
import com.mu.lastkey.core.database.getJsonColumnAdapter
import com.mu.lastkey.core.database.getLocalDateTimeColumnAdapter
import commulastkeycoredata.CredentialEntity
import commulastkeycoredata.CredentialPagingEntity
import commulastkeycoredata.CredentialSectionEntity
import commulastkeycoredata.CredentialSectionFieldEntity
import kotlinx.serialization.json.Json

class AppDatabaseFactory(
    private val driver: SqlDriver,
    private val json: Json
) {
    fun create(): AppDatabase {
        return AppDatabase(
            driver = driver,
            credentialEntityAdapter = CredentialEntity.Adapter(
                createdAtAdapter = getLocalDateTimeColumnAdapter(),
                updatedAtAdapter = getLocalDateTimeColumnAdapter()
            ),
            credentialSectionEntityAdapter = CredentialSectionEntity.Adapter(
                createdAtAdapter = getLocalDateTimeColumnAdapter(),
                updatedAtAdapter = getLocalDateTimeColumnAdapter()
            ),
            credentialSectionFieldEntityAdapter = CredentialSectionFieldEntity.Adapter(
                createdAtAdapter = getLocalDateTimeColumnAdapter(),
                updatedAtAdapter = getLocalDateTimeColumnAdapter()
            ),
            credentialPagingEntityAdapter = CredentialPagingEntity.Adapter(
                offsetIdsAdapter = getJsonColumnAdapter(json)
            )
        )
    }
}
