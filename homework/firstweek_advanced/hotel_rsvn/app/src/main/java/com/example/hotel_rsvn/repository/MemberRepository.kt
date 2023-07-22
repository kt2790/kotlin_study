package com.example.hotel_rsvn.repository

interface MemberRepository {
    val memberList: MutableMap<String, Member>
    fun addMember(name: String): Member?
    fun findMember(name: String): Member?
    fun updateMember(name: String, balance: Int, usedBalance: Int): Boolean
    fun getMemberUsedBalance(name: String): Int?
}