package com.vannak.tech.api_project.service

import com.vannak.tech.api_project.api.exception.IDNotFoundException
import com.vannak.tech.api_project.domain.model.User
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList

@Component
class UserService {
//    private var users : MutableList<User> = mutableListOf<User>()
private var users : ArrayList<User> = ArrayList()
    private var userCount:Int=3;
//    init {
//        users.add(User(1, "User 1", Date(), "+85512345678", "test1@gmail.com"))
//        users.add(User(2, "User 2", Date(), "+855962867484", "test2@gmail.com"))
//        users.add(User(3, "User 3", Date(), "+85521212122", "test3@gmail.com"))
//    }

    fun findAll(): ResponseEntity<Any>{
        return ResponseEntity.ok(users)
    }

    fun save(user: User): User {
        if (user.getId()==0){
            user.setId(++userCount)
        }
        users.add(user)
        return user
    }

    fun findOne(id:Int): User {
        for (user in users){
            if (user.getId()==id){
                return user
            }
        }
        throw IDNotFoundException("userId")
    }

    fun deleteById(id: Int): User {
        var itr = users.iterator()
        val user = itr.next()
        while (itr.hasNext()){
            itr.remove()
            return user
        }
        return null!!
    }

}