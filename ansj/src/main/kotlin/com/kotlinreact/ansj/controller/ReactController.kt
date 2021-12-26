package com.kotlinreact.ansj.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("ansj")
class ReactController {
    @GetMapping
    fun view(): String{
        return "index"
    }
}