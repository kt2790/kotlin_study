package com.example.hotel_rsvn

const val INITIAL_MONEY = 100000

class MemberServiceImpl private constructor() : MemberService {
    override val memberList: MutableMap<String, Member> = mutableMapOf<String, Member>()

    override fun addMember(name: String): Member? {
        memberList[name] = Member(name, INITIAL_MONEY)
        return memberList[name]
    }

    override fun findMember(name: String): Member? {
        return if (memberList.contains(name)) memberList[name] else null
    }

    override fun getMemberUsedBalance(name: String): Int? {
        return findMember(name)?.run {usedBalance}
    }

    companion object {
        private var instance: MemberServiceImpl? = null

        fun getInstance() : MemberServiceImpl {
            return instance ?: MemberServiceImpl().also {
                instance = it
            }
        }
    }
}