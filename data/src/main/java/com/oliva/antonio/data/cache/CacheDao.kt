package com.oliva.antonio.data.cache

import io.reactivex.Flowable

/**
 * Generic cache dao methods
 * Created by antonio on 12/3/17.
 */
interface CacheDao<T> {

    fun insert(entities: List<T>): List<Long>

    fun get(id: Int): Flowable<T>

    fun getAll(offset: Int, limit: Int): Flowable<List<T>>

    fun getFilteredByField(offset: Int, filter: String, limit: Int): Flowable<List<T>>

    fun getInRange(columnName: String, values: List<String>): Flowable<List<T>>

    fun update(entity: T): Flowable<T>

    fun updateAll(entities: List<T>): Flowable<List<T>>
}
