package com.example.layoutpractice.service

import com.example.layoutpractice.model.Member

interface MemberService {

    fun addMember(id: String, pw: String, name: String, age: Int, mbti: String) : Boolean
    fun updateMemberById(id: String, pw: String, name: String, age: Int, mbti: String) : Boolean
    fun deleteMemberById(id: String) : Boolean
    fun findMemberById(id: String) : Member?

    fun login(id: String, pw: String) : String?

    fun getMemberNameById(id: String): String?
    fun getMemberAgeById(id: String): Int?
    fun getMemberMbtiById(id: String): String?
}