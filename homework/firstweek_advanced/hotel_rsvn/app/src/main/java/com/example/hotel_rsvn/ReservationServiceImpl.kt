package com.example.hotel_rsvn

const val COST = 1000

class ReservationServiceImpl private constructor (override val memberService: MemberService) : ReservationService {
    override val reservationList: ArrayList<Reservation> = ArrayList<Reservation>()
    private var id: Int = 0

    override fun addReservation(name: String, roomNo: Int, checkInDate: Int, checkOutDate: Int) : Int{
        var member = memberService.findMember(name) ?: memberService.addMember(name)
        val balance = member!!.balance

        if (balance < COST) {
            println("돈이 부족합니다.")
            return 1
        }

        reservationList.add(Reservation(name, roomNo, checkInDate, checkOutDate, ++id))

        member.balance -= COST
        member.usedBalance += COST

        return 0
    }

    override fun dropReservationById(id: Int): Boolean {
        return findReservationById(id)?.run {
            reservationList.remove(this)
            true
        } ?: false
    }

    override fun findReservationById(id: Int): Reservation? {
        return reservationList.filter {it.id == id}.run {
            if(this.isNotEmpty()) this[0] else null
        }
    }

    override fun findReservationByName(name: String): List<Reservation>? {
        return reservationList.filter {it.name == name}.run {
            if(this.isNotEmpty()) this else null
        }
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

    override fun showUsedBalanceByName(name: String) {
        memberService.getMemberUsedBalance(name)?.run {
            println("1. 초기 금액으로 $INITIAL_MONEY 원 입급되었습니다.")
            println("2. 예약금으로 $this 원 출금되었습니다.")
        } ?: println("예약된 사용자를 찾을 수 없습니다.")
    }

    override fun checkInRoomAvailable(roomNo: Int, checkInDate: Int) : Boolean {
        return !reservationList.filter {it.roomNo == roomNo}.any {checkInDate in it.checkInDate..it.checkOutDate}
    }

    override fun checkRoomAvailable(roomNo: Int, checkInDate: Int, checkOutDate: Int) : Boolean {
        return reservationList.filter {it.roomNo == roomNo}.all {it.checkOutDate < checkInDate || it.checkInDate > checkOutDate}
    }

    companion object {
        private var instance: ReservationServiceImpl? = null

        fun getInstance() : ReservationServiceImpl {
            return instance ?: ReservationServiceImpl(MemberServiceImpl.getInstance()).also {
                instance = it
            }
        }
    }

}
