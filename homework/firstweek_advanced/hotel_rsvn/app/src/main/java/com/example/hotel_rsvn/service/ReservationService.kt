package com.example.hotel_rsvn.service

import com.example.hotel_rsvn.repository.MemberRepository
import com.example.hotel_rsvn.repository.Reservation
import com.example.hotel_rsvn.repository.ReservationRepository

interface ReservationService {
    fun addReservation(name: String, roomNo: Int, checkInDate: Int, checkOutDate: Int)
    fun dropReservationById(id: Int): Boolean

    fun findReservationById(id: Int) : Reservation?
    fun findReservationByName(name: String) : List<Reservation>?

    fun showReservationList()
    fun showSortedReservationList()
    fun showUsedBalanceByName(name: String)

    fun checkInRoomAvailable(roomNo: Int, checkInDate: Int) : Boolean
    fun checkRoomAvailable(roomNo: Int, checkInDate: Int, checkOutDate: Int) : Boolean
}