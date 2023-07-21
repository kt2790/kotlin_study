package com.example.hotel_rsvn

interface MemberService {
    val memberList: MutableMap<String, Member>
    fun addMember(name: String): Member?
    fun findMember(name: String): Member?
    fun getMemberUsedBalance(name: String): Int?
}