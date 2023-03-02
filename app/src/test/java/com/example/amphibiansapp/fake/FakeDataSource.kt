package com.example.amphibiansapp.fake

import com.example.amphibiansapp.model.Amphibian

object FakeDataSource {
    const val nameOne = "name one"
    const val nameTwo = "name two"
    const val typeOne = "type one"
    const val typeTwo = "type two"
    const val descOne = "first long desc"
    const val descTwo = "second long desc"
    const val imgSrcOne = "url.1"
    const val imgSrcTwo = "url.2"

    val amphibiansList = listOf(Amphibian(nameOne, typeOne, descOne, imgSrcOne), Amphibian(
        nameTwo, typeTwo, descTwo, imgSrcTwo)
    )
}