package com.example.ujian_network_and_design_patern_farhan_fadhilah.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.User

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun register(user: User): Long

    @Query("SELECT * FROM User")
    fun getAllUser(): List<User>

    @Query("SELECT * FROM User WHERE email=:email")
    fun getUserByEmail(email: String): User

    @Query("SELECT * FROM User WHERE userId=:userId")
    fun getUserById(userId: Int): User
}