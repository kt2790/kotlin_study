package com.example.hotel_rsvn.repository

const val COST = 1000

class ReservationRepositoryImpl private constructor() :
    ReservationRepository {
    override val reservationList: ArrayList<Reservation> = ArrayList<Reservation>()
    private var id: Int = 0

    override fun addReservation(name: String, roomNo: Int, checkInDate: Int, checkOutDate: Int) {
        reservationList.add(Reservation(name, roomNo, checkInDate, checkOutDate, ++id))
    }

    override fun dropReservationById(id: Int): Boolean {
        return findReservationById(id)?.let {
            reservationList.remove(it)
            true
        } ?: false
    }

    override fun findReservationById(id: Int): Reservation? {
        return reservationList.filter {it.id == id}.let {
            if(it.isNotEmpty()) it[0] else null
        }
    }

    override fun findReservationByName(name: String): List<Reservation>? {
        return reservationList.filter {it.name == name}.ifEmpty { null }
    }

    override fun showReservationList() {
        println("호텔 예약자 목록입니다.")

        reservationList.forEachIndexed() {i, it ->
            println("${i + 1}. 사용자: ${it.name}, 방번호: ${it.roomNo}, 체크인: ${it.checkInDate}, 체크아웃: ${it.checkOutDate}")
        }
    }

    override fun showSortedReservationList() {
        println("호텔 예약자 목록입니다. (정렬완료)")

        reservationList.sortedWith(compareBy({it.checkInDate})).forEachIndexed() {i, it ->
            println("${i + 1}. 사용자: ${it.name}, 방번호: ${it.roomNo}, 체크인: ${it.checkInDate}, 체크아웃: ${it.checkOutDate}")
        }
    }

    override fun checkInRoomAvailable(roomNo: Int, checkInDate: Int) : Boolean {
        return !reservationList.filter {it.roomNo == roomNo}.any {checkInDate in it.checkInDate..it.checkOutDate}
    }

    override fun checkRoomAvailable(roomNo: Int, checkInDate: Int, checkOutDate: Int) : Boolean {
        return reservationList.filter {it.roomNo == roomNo}.all {it.checkOutDate < checkInDate || it.checkInDate > checkOutDate}
    }

    companion object {
        private var instance: ReservationRepositoryImpl? = null

        fun getInstance() : ReservationRepositoryImpl {
            return instance ?: ReservationRepositoryImpl().also {
                instance = it
            }
        }
    }

}
