package com.quiz.client.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User {

    @PrimaryKey
    val uid:Int

    @ColumnInfo(name = "nickname")
    val nickName:String?

    constructor(uid:Int,nickName: String) {
        this.uid = uid
        this.nickName = nickName
    }
}