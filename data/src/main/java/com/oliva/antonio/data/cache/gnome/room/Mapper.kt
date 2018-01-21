package com.oliva.antonio.data.cache.gnome.room

import com.oliva.antonio.data.entity.GnomeEntity

/**
 * Created by antonio on 12/3/17.
 */

internal fun mapGnomeRoomToGenomeEntity(gnome: GnomeRoomEntity): GnomeEntity {
    val professions: List<String> = parseStringToListString(gnome.professions)
    val friends: List<String> = parseStringToListString(gnome.friends)

    return GnomeEntity(gnome.id,
            gnome.name,
            gnome.thumbnails,
            gnome.age,
            gnome.weight,
            gnome.height,
            gnome.hairColor,
            professions,
            friends,
            gnome.annotation)
}

internal fun mapGnomeEntityToGnomeRoomEntity(gnome: GnomeEntity): GnomeRoomEntity {
    val professions: String = parseListStringToString(gnome.professions)
    val friends: String = parseListStringToString(gnome.friends)

    return GnomeRoomEntity(gnome.id,
            gnome.name,
            gnome.thumbnail,
            gnome.age,
            gnome.weight,
            gnome.height,
            gnome.hairColor,
            professions,
            friends)
}
