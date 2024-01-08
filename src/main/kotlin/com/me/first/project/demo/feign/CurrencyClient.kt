package com.me.first.project.demo.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(value = "currencyClient", url = "https://belarusbank.by/api")
interface CurrencyClient {

    @RequestMapping(method = [RequestMethod.GET], value = ["/kursExchange?city=Орша"])
    fun getCurrencyOrsha():List<CurrencyDetails>

    @RequestMapping(method = [RequestMethod.GET], value = ["/kursExchange?city=Могилев"])
    fun getCurrencyMogilev():List<CurrencyDetails>

    @RequestMapping(method = [RequestMethod.GET], value = ["/kursExchange?city=Брест"])
    fun getCurrencyBrest():List<CurrencyDetails>
}