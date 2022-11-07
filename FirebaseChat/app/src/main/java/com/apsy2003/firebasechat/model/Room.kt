package com.apsy2003.firebasechat.model

class Room {
    var id: String = "" //방아이디
    var title: String = "" //방이름
    var users: String = ""

    constructor()

    constructor(title:String, creatorName: String){
        this.title = title
        users = creatorName
    }
}