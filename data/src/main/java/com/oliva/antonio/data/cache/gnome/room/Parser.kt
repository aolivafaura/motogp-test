package com.oliva.antonio.data.cache.gnome.room

/**
 * Created by antoniojoseolivafaura on 07/12/2017.
 */

internal fun parseListStringToString(strings: List<String>?): String {
    return if (strings?.isEmpty() != false) "" else strings.let {
        strings.joinToString { it.trim() }
    }
}

private const val LIST_DELIMITER = ", "

internal fun parseStringToListString(string: String?): List<String> {
    return if (string.isNullOrEmpty()) emptyList() else string!!.split(LIST_DELIMITER)
}