package com.example.layoutpractice.service

import com.example.layoutpractice.model.Member
import com.example.layoutpractice.repository.MemberRepository
import com.example.layoutpractice.repository.MemberRepositoryImpl

class MemberServiceImpl private constructor(private val memberRepository: MemberRepository) : MemberService {
    override fun addMember(id: String, pw: String, name: String, age: Int, mbti: String): Boolean {
        return memberRepository.addMember(id, pw, name, age, mbti)
    }

    override fun updateMemberById(
        id: String,
        pw: String,
        name: String,
        age: Int,
        mbti: String
    ): Boolean {
        return memberRepository.updateMemberById(id, pw, name, age, mbti)
    }

    override fun deleteMemberById(id: String): Boolean {
        return memberRepository.deleteMemberById(id)
    }

    override fun findMemberById(id: String): Member? {
        return memberRepository.findMemberById(id)
    }

    override fun login(id: String, pw: String): String? {
        return memberRepository.findMemberById(id)?.let{ if(pw == it.pw) id else null }
    }

    override fun getMemberNameById(id: String): String? {
        return memberRepository.findMemberById(id)?.name
    }

    override fun getMemberAgeById(id: String): Int? {
        return memberRepository.findMemberById(id)?.age
    }

    override fun getMemberMbtiById(id: String): String? {
        return memberRepository.findMemberById(id)?.mbti
    }

    companion object {
        private var instance: MemberServiceImpl? = null

        fun getInstance(): MemberServiceImpl {
            return instance ?: synchronized(this) { MemberServiceImpl(MemberRepositoryImpl.getInstance()).also {
                instance = it
               }
            }
        }
    }
}