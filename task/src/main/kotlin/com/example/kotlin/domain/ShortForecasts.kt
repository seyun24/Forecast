package com.example.kotlin.domain

import javax.persistence.*

@Entity(name = "ShortForecasts")
class ShortForecasts (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = true)
    var category : String,

    @Column(name = "fcstValue", nullable = true)
    var fcstValue: String,
)