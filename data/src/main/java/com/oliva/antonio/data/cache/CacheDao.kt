package com.oliva.antonio.data.cache

import io.reactivex.Flowable

/**
 * Generic cache dao methods
 * Created by antonio on 12/3/17.
 */
interface CacheDao<T> {

    fun insert(entities: List<T>): List<Long>

    fun get(id: Int): Flowable<T>

    fun getAll(): Flowable<List<T>>

    fun update(entity: T): T

    fun updateAll(entities: List<T>): List<T>
}
