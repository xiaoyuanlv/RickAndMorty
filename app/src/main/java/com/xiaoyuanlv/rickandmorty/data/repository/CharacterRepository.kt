package com.xiaoyuanlv.rickandmorty.data.repository

import com.xiaoyuanlv.rickandmorty.data.entities.Character
import com.xiaoyuanlv.rickandmorty.data.local.CharacterDao
import com.xiaoyuanlv.rickandmorty.data.remote.CharacterRemoteDataSource
import com.xiaoyuanlv.rickandmorty.utils.performGetOperation
import javax.inject.Inject

class CharacterRepository @Inject constructor(
   private val remoteDataSource: CharacterRemoteDataSource,
   private val localDataSource: CharacterDao
) {
   fun getCharacter(id: Int) = performGetOperation(
      databaseQuery = { localDataSource.getCharacter(id) },
      networkCall = { remoteDataSource.getCharacter(id) },
      saveCallResult = { localDataSource.insert(it as Character) }
   )

   fun getCharacters() = performGetOperation(
      databaseQuery = { localDataSource.getAllCharacters() },
      networkCall = { remoteDataSource.getCharacters() },
      saveCallResult = { localDataSource.insertAll(it.results) }
   )
}