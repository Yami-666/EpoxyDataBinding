package com.example.epoxydatabinding.models

data class Text(
    override val size: String,
    override val path: String,
    override val name: String,
    override val createTime: String,
) : Content()