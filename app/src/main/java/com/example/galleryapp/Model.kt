package com.example.galtest

import java.io.Serializable

class Model : Serializable{
    var name: String? = null
    var image: Int = 0

    constructor(name:String, image:Int){
        this.name = name
        this.image = image
    }
}