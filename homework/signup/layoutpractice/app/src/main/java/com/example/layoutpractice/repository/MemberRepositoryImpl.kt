package com.example.layoutpractice.repository

import com.example.layoutpractice.model.Member

class MemberRepositoryImpl private constructor() : MemberRepository {

    private val memberList = mutableMapOf<String, Member>()

    override fun addMember(id: String, pw: String, name: String, age: Int, mbti: String) : Boolean {
        return memberList[id]?.let { false }
            ?: run { memberList[id] = Member(id, pw, name, age, mbti)
                     true}
    }

    override fun updateMemberById(id: String, pw: String, name: String, age: Int, mbti: String) : Boolean{
        return memberList[id]?.let { memberList[id] = Member(id, pw, name, age, mbti)
                                     true} ?: false
    }

    override fun deleteMemberById(id: String) : Boolean {
        return memberList[id]?.let { memberList.remove(id)
                                     true} ?: false
    }

    override fun findMemberById(id: String): Member? {
        return memberList[id]
    }

    companion object {
        private var instance: MemberRepositoryImpl? = null

        fun getInstance(): MemberRepositoryImpl {
            return instance ?: synchronized(this) { MemberRepositoryImpl().also {
                    instance = it
                }
            }
        }
    }
}