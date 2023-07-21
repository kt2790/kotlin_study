package com.example.hotel_rsvn

const val YEAR_SIZE = 8
val reservationServiceImpl: ReservationServiceImpl = ReservationServiceImpl.getInstance()

fun main() {
    var name: String?
    var roomNo: Int?
    var checkInDate: Int?
    var checkOutDate: Int?

    while(true) {
        println("호텔예약 프로그램 입니다.")
        println("메뉴")
        println("1. 방예약, 2. 예약목록 출력, 3. 예약목록 (정렬) 출력, 4. 시스템 종료, 5. 금액 입금-출금 내역 목록 출력, 6. 예약 변경/취소")

        val cmd = Integer.parseInt(readLine().run {
            if (this.isNullOrEmpty()) "0" else this
        })

        when(cmd) {
            1 -> {
                while (true) {
                    println("예약자 분의 성함을 입력해주세요")
                    name = readLine()

                    if (!name.isNullOrEmpty()) {
                        break
                    }
                    println("올바른 성함을 입력해주세요")
                }

                while (true) {
                    println("예약할 방번호를 입력해주세요")
                    roomNo = Integer.parseInt(readLine().run {
                        if (this.isNullOrEmpty()) "0" else this
                    })

                    if (roomNo in 100..999) {
                        break
                    }
                    println("올바르지 않은 방번호 입니다. 방번호는 100~999 영역 이내입니다.")
                }


                while (true) {
                    println("체크인 날짜를 입력해주세요 표기형식. 20230631")
                    checkInDate = Integer.parseInt(readLine().run {
                        if (this.isNullOrEmpty()) "0" else this
                    })

                    if (checkInDate.toString().length == YEAR_SIZE) {
                        if (reservationServiceImpl.checkInRoomAvailable(roomNo!!, checkInDate)) {
                            break
                        }
                        println("해당 날짜에 이미 방을 사용중입니다. 다른날짜를 입력해주세요")
                        continue
                    }
                    println("올바르지 않은 날짜 형식입니다.")
                }

                while (true) {
                    println("체크아웃 날짜를 입력해주세요 표기형식. 20230631")
                    checkOutDate = Integer.parseInt(readLine().run {
                        if (this.isNullOrEmpty()) "0" else this
                    })

                    if (checkOutDate.toString().length == YEAR_SIZE) {
                        if (checkInDate!! <= checkOutDate) {
                            if (reservationServiceImpl.checkRoomAvailable(
                                    roomNo!!,
                                    checkInDate,
                                    checkOutDate
                                )
                            ) {
                                break
                            }
                            println("해당 날짜에 이미 방을 사용중입니다. 다른날짜를 입력해주세요")
                            continue
                        }
                        println("체크인 날짜보다 이전이거나 같을 수는 없습니다.")
                        continue
                    }
                    println("올바르지 않은 날짜 형식입니다.")
                }

                reservationServiceImpl.addReservation(
                    name!!,
                    roomNo!!,
                    checkInDate!!,
                    checkOutDate!!
                )
                println("호텔 예약이 완료되었습니다.")
            }

            2 -> reservationServiceImpl.showReservationList()

            3 -> reservationServiceImpl.showSortedReservationList()

            4 -> break

            5 -> {
                while (true) {
                    println("조회하실 사용자 이름을 입력하세요.")
                    name = readLine()

                    if (!name.isNullOrEmpty()) {
                        break
                    }
                    println("올바른 이름을 입력해주세요")
                }

                reservationServiceImpl.showUsedBalanceByName(name!!)
            }

            6 -> {
                while (true) {
                    println("조회하실 사용자 이름을 입력하세요.")
                    name = readLine()

                    if (!name.isNullOrEmpty()) {
                        break
                    }
                    println("올바른 이름을 입력해주세요")
                }

                reservationServiceImpl.findReservationByName(name!!)?.run {
                    while (true) {
                        println("$name 님이 예약한 목록입니다. 변경하실 예약번호를 입력해주세요 (탈출은 exit입력)")

                        forEachIndexed() { i, it ->
                            println("${i + 1}. 방번호: ${it.roomNo}, 체크인: ${it.checkInDate}, 체크아웃: ${it.checkOutDate}")
                        }

                        val idx = readLine().let {
                            if (it.isNullOrEmpty()) 0 else if (it == "exit") -1 else Integer.parseInt(it)
                        }

                        if (idx == -1) {
                            break
                        }
                        else if (idx !in 1..size) {
                            println("범위에 없는 예약번호 입니다.")
                            continue
                        }

                        println("해당 예약을 어떻게 하시겠어요 1. 변경  2. 취소 / 이외 번호. 메뉴로 돌아가기")

                        when(readLine().let {if (it.isNullOrEmpty()) 0 else Integer.parseInt(it)}) {
                            1 -> {
                                reservationServiceImpl.dropReservationById(this[idx - 1].id)

                                while (true) {
                                    println("예약할 방번호를 입력해주세요")
                                    roomNo = Integer.parseInt(readLine().run {
                                        if (this.isNullOrEmpty()) "0" else this
                                    })

                                    if (roomNo in 100..999) {
                                        break
                                    }
                                    println("올바르지 않은 방번호 입니다. 방번호는 100~999 영역 이내입니다.")
                                }


                                while (true) {
                                    println("체크인 날짜를 입력해주세요 표기형식. 20230631")
                                    checkInDate = Integer.parseInt(readLine().run {
                                        if (this.isNullOrEmpty()) "0" else this
                                    })

                                    if (checkInDate.toString().length == YEAR_SIZE) {
                                        if (reservationServiceImpl.checkInRoomAvailable(roomNo!!, checkInDate!!)) {
                                            break
                                        }
                                        println("해당 날짜에 이미 방을 사용중입니다. 다른날짜를 입력해주세요")
                                        continue
                                    }
                                    println("올바르지 않은 날짜 형식입니다.")
                                }

                                while (true) {
                                    println("체크아웃 날짜를 입력해주세요 표기형식. 20230631")
                                    checkOutDate = Integer.parseInt(readLine().run {
                                        if (this.isNullOrEmpty()) "0" else this
                                    })

                                    if (checkOutDate.toString().length == YEAR_SIZE) {
                                        if (checkInDate!! <= checkOutDate!!) {
                                            if (reservationServiceImpl.checkRoomAvailable(
                                                    roomNo!!,
                                                    checkInDate!!,
                                                    checkOutDate!!
                                                )
                                            ) {
                                                break
                                            }
                                            println("해당 날짜에 이미 방을 사용중입니다. 다른날짜를 입력해주세요")
                                            continue
                                        }
                                        println("체크인 날짜보다 이전이거나 같을 수는 없습니다.")
                                        continue
                                    }
                                    println("올바르지 않은 날짜 형식입니다.")
                                }

                                reservationServiceImpl.addReservation(
                                    name!!,
                                    roomNo!!,
                                    checkInDate!!,
                                    checkOutDate!!
                                )
                                println("호텔 예약이 완료되었습니다.")
                                break

                            }
                            2 -> {
                                reservationServiceImpl.dropReservationById(this[idx - 1].id)
                                println("취소가 완료되었습니다.")
                                break
                            }

                            else -> break
                        }


                    }
                } ?: println("예약된 사용자를 찾을 수 없습니다.")

            }

            else -> {
                println("지원하지 않는 기능입니다. 다른 메뉴를 선택해주세요.")
            }

        }
        println()
    }
}
