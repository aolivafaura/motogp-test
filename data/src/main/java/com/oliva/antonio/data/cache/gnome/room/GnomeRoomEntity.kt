package com.oliva.antonio.data.cache.gnome.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by antonio on 12/3/17.
 */

@Entity(tableName = "gnomes")
data class GnomeRoomEntity(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
                           @ColumnInfo(name = "name") var name: String,
                           @ColumnInfo(name = "thumbnail") var thumbnails: String?,
                           @ColumnInfo(name = "age") var age: Int,
                           @ColumnInfo(name = "weight") var weight: Double,
                           @ColumnInfo(name = "height") var height: Double,
                           @ColumnInfo(name = "hair_color") var hairColor: String,
                           @ColumnInfo(name = "professions") var professions: String?,
                           @ColumnInfo(name = "friends") var friends: String?,
                           @ColumnInfo(name = "annotation") var annotation: String = "")
