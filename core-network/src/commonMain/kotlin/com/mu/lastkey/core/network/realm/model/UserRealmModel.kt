package com.mu.lastkey.core.network.realm.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class UserRealmModel : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var email: String = ""
}
