package com.example.moregetandpostrequests


class Data : ArrayList<Data.UsersDataItem>(){
    data class UsersDataItem(
        val location: String,
        val name: String
    )
}