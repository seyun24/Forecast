package com.example.kotlin.domain

import javax.persistence.*

@Entity(name = "MiddleForecasts")
class MiddleForecasts (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "wfSv", nullable =true, columnDefinition = "TEXT")
    var wfSv : String
)