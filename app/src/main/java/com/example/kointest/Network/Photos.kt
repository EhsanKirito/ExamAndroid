package com.example.kointest.Network

import com.example.kointest.Network.Photo

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<Photo>,
    val total: Int
)