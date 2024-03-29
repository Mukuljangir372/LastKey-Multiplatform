package com.mu.lastkey.core.network.realm.model

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

@Suppress("PropertyName")
class CredentialRealmModel : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var createdAt: RealmInstant = RealmInstant.now()
    var updatedAt: RealmInstant = RealmInstant.now()
}
