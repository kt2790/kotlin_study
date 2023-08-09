package com.example.hotel_rsvn.repository

const val INITIAL_MONEY = 100000

class MemberRepositoryImpl private constructor() : MemberRepository {
    override val memberList: MutableMap<String, Member> = mutableMapOf<String, Member>()

    override fun addMember(name: String): Member? {
        memberList[name] = Member(name, INITIAL_MONEY)
        return memberList[name]
    }

    override fun findMember(name: String): Member? {
        return memberList[name]
    }

    override fun updateMember(name: String, balance: Int, usedBalance: Int): Member? {
        memberList[name] = Member(name, balance, usedBalance)
        return memberList[name]
    }

    override fun getMemberUsedBalance(name: String): Int? {
        return findMember(name)?.usedBalance
    }

    companion object {
        private var instance: MemberRepositoryImpl? = null

        fun getInstance() : MemberRepositoryImpl {
            return instance ?: MemberRepositoryImpl().also {
                instance = it
            }
        }
    }
}