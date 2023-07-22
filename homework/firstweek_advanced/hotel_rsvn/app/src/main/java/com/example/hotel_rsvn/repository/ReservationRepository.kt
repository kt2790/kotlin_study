package com.example.hotel_rsvn.repository

interface ReservationRepository {
    val reservationList: ArrayList<Reservation>

    fun addReservation(name: String, roomNo: Int, checkInDate: Int, checkOutDate: Int)
    fun dropReservationById(id: Int): Boolean

    fun findReservationById(id: Int) : Reservation?
    fun findReservationByName(name: String) : List<Reservation>?

    fun showReservationList()
    fun showSortedReservationList()

    fun checkInRoomAvailable(roomNo: Int, checkInDate: Int) : Boolean
    fun checkRoomAvailable(roomNo: Int, checkInDate: Int, checkOutDate: Int) : Boolean
}
