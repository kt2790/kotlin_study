package com.example.hotel_rsvn

interface ReservationService {
    val reservationList: ArrayList<Reservation>
    val memberService: MemberService

    fun addReservation(name: String, roomNo: Int, checkInDate: Int, checkOutDate: Int): Int
    fun dropReservationById(id: Int): Boolean

    fun findReservationById(id: Int) : Reservation?
    fun findReservationByName(name: String) : List<Reservation>?

    fun showReservationList()
    fun showSortedReservationList()
    fun showUsedBalanceByName(name: String)

    fun checkInRoomAvailable(roomNo: Int, checkInDate: Int) : Boolean
    fun checkRoomAvailable(roomNo: Int, checkInDate: Int, checkOutDate: Int) : Boolean
}