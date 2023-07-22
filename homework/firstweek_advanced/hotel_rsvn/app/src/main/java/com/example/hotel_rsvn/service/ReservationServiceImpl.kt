package com.example.hotel_rsvn.service

import com.example.hotel_rsvn.repository.COST
import com.example.hotel_rsvn.repository.INITIAL_MONEY
import com.example.hotel_rsvn.repository.MemberRepository
import com.example.hotel_rsvn.repository.MemberRepositoryImpl
import com.example.hotel_rsvn.repository.Reservation
import com.example.hotel_rsvn.repository.ReservationRepository
import com.example.hotel_rsvn.repository.ReservationRepositoryImpl

class ReservationServiceImpl private constructor (
    override val memberRepository: MemberRepository,
    override val reservationRepository: ReservationRepository
) : ReservationService {

    override fun addReservation(name: String, roomNo: Int, checkInDate: Int, checkOutDate: Int) {
        val member = memberRepository.findMember(name) ?: memberRepository.addMember(name)
        val balance = member!!.balance
        val usedBalance = member.usedBalance

        if (balance < COST) {
            println("돈이 부족합니다.")
            return
        }

        memberRepository.updateMember(name, balance - COST, usedBalance + COST)
        reservationRepository.addReservation(name, roomNo, checkInDate, checkOutDate)
    }

    override fun dropReservationById(id: Int): Boolean {
        return reservationRepository.dropReservationById(id)
    }

    override fun findReservationById(id: Int): Reservation? {
        return reservationRepository.findReservationById(id)
    }

    override fun findReservationByName(name: String): List<Reservation>? {
        return reservationRepository.findReservationByName(name)
    }

    override fun showReservationList() {
        reservationRepository.showReservationList()
    }

    override fun showSortedReservationList() {
        reservationRepository.showSortedReservationList()
    }

    override fun showUsedBalanceByName(name: String) {
        memberRepository.findMember(name)?.let {
            println("1. 초기 금액으로 $INITIAL_MONEY 원 입급되었습니다.")
            println("2. 예약금으로 ${it.usedBalance} 원 출금되었습니다.")
        } ?: println("예약된 사용자를 찾을 수 없습니다.")
    }

    override fun checkInRoomAvailable(roomNo: Int, checkInDate: Int): Boolean {
        return reservationRepository.checkInRoomAvailable(roomNo, checkInDate)
    }

    override fun checkRoomAvailable(roomNo: Int, checkInDate: Int, checkOutDate: Int): Boolean {
        return reservationRepository.checkRoomAvailable(roomNo, checkInDate, checkOutDate)
    }

    companion object {
        private var instance: ReservationServiceImpl? = null

        fun getInstance() : ReservationService {
            return instance ?: ReservationServiceImpl(MemberRepositoryImpl.getInstance(), ReservationRepositoryImpl.getInstance()).also {
                instance = it
            }
        }
    }
}