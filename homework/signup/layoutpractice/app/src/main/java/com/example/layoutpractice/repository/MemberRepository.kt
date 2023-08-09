package com.example.layoutpractice.repository

import com.example.layoutpractice.model.Member

interface MemberRepository {

    fun addMember(id: String, pw: String, name: String, age: Int, mbti: String) : Boolean
    fun updateMemberById(id: String, pw: String, name: String, age: Int, mbti: String) : Boolean
    fun deleteMemberById(id: String) : Boolean
    fun findMemberById(id: String) : Member?

}