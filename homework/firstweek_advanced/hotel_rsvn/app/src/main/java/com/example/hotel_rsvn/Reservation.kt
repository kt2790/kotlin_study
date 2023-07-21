package com.example.hotel_rsvn

data class Reservation(var name: String, var roomNo: Int, var checkInDate: Int, var checkOutDate: Int, val id: Int) {
}