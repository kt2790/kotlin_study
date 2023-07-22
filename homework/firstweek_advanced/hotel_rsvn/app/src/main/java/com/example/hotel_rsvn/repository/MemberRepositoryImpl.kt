package com.example.hotel_rsvn.repository

const val INITIAL_MONEY = 100000

class MemberRepositoryImpl private constructor() : MemberRepository {
    override val memberList: MutableMap<String, Member> = mutableMapOf<String, Member>()

    override fun addMember(name: String): Member? {
        memberList[name] = Member(name, INITIAL_MONEY)
        return memberList[name]
    }

    override fun findMember(name: String): Member? {
        return if (memberList.contains(name)) memberList[name] else null
    }

    override fun updateMember(name: String, balance: Int, usedBalance: Int): Boolean {
        if (memberList.contains(name)) {
            memberList[name] = Member(name, balance, usedBalance)
            return true
        } else return false
    }

    override fun getMemberUsedBalance(name: String): Int? {
        return findMember(name)?.run {usedBalance}
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