package com.oliva.antonio.data.cache.gnome.room

import com.oliva.antonio.data.cache.CacheDao
import com.oliva.antonio.data.entity.GnomeEntity
import io.reactivex.Flowable
import java.util.*

/**
 * Created by antonio on 12/3/17.
 */

class CacheGnomeDaoWrapper(val gnomeDao: CacheGnomeDao) : CacheDao<GnomeEntity> {

    override fun getInRange(columnName: String, values: List<String>): Flowable<List<GnomeEntity>> {
        return gnomeDao.getWithName(values).map { it.map { mapGnomeRoomToGenomeEntity(it) } }
    }

    override fun insert(entities: List<GnomeEntity>): List<Long> {
        return gnomeDao.insert(entities.map { mapGnomeEntityToGnomeRoomEntity(it) })
    }

    override fun getAll(offset: Int, limit: Int): Flowable<List<GnomeEntity>> {
        return gnomeDao.getAll(offset, limit).map { it.map { mapGnomeRoomToGenomeEntity(it) } }
    }

    override fun get(id: Int): Flowable<GnomeEntity> {
        return gnomeDao.get(id).map { mapGnomeRoomToGenomeEntity(it) }
    }

    override fun getFilteredByField(offset: Int, filter: String, limit: Int): Flowable<List<GnomeEntity>> {
        return gnomeDao.getFilteredByName(offset, filter, limit).map { it.map { mapGnomeRoomToGenomeEntity(it) } }
    }

    override fun update(entity: GnomeEntity): Flowable<GnomeEntity> {
        // Get record from database
        return gnomeDao.get(entity.id).map {
            // Update record with new fields
            it.name = entity.name
            it.professions = parseListStringToString(entity.professions)
            it.friends = parseListStringToString(entity.friends)
            it.hairColor = entity.hairColor
            it.height = entity.height
            it.weight = entity.weight
            it.thumbnails = entity.thumbnail
            it.age = entity.age
            it.annotation = entity.annotation

            // Insert updated record
            gnomeDao.insert(Collections.singletonList(it))
            // Return it
            mapGnomeRoomToGenomeEntity(it)
        }
    }

    override fun updateAll(entities: List<GnomeEntity>): Flowable<List<GnomeEntity>> {
        // Get every record on database
        return gnomeDao.getAll(0, entities.size).map {
            // Generate a map with key:id and value:entity
            val localMap: MutableMap<Int, GnomeRoomEntity> = HashMap(it.associateBy({ it.id }, { it }))

            var localGnomeAux: GnomeRoomEntity?
            entities.forEach {
                localGnomeAux = localMap[it.id]
                // If exists a record with given id, update with data from server
                if (localGnomeAux != null) {
                    localGnomeAux!!.name = it.name
                    localGnomeAux!!.professions = parseListStringToString(it.professions)
                    localGnomeAux!!.friends = parseListStringToString(it.friends)
                    localGnomeAux!!.hairColor = it.hairColor
                    localGnomeAux!!.height = it.height
                    localGnomeAux!!.weight = it.weight
                    localGnomeAux!!.thumbnails = it.thumbnail
                    localGnomeAux!!.age = it.age

                    // Update local fields with database value. In this case, just annotation field
                    it.annotation = localGnomeAux!!.annotation
                } else {
                    // If record is not present on database, add to list in order of insert it
                    localMap.put(it.id, mapGnomeEntityToGnomeRoomEntity(it))
                }
            }
            // Insert updated records on database
            gnomeDao.insert(localMap.map { it.value })
            // Return values
            localMap.map { it.value }.map { mapGnomeRoomToGenomeEntity(it) }
        }
    }
}
