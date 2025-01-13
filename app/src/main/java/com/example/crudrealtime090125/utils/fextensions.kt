package com.example.crudrealtime090125.utils

fun String.encodeEmail()=this.replace("@", "_AT_").replace(".", "_DOT_")
