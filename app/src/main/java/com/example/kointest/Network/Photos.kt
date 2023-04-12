package com.example.kointest.Network

import com.example.kointest.Network.Photox

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<Photox>,
    val total: Int
)