package org.hse.template.client.rest.api

import org.hse.template.client.rest.model.HolidayResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "calendarific")
interface CalendarificClient {

    @GetMapping("/holidays")
    fun getHolidays(
        @RequestParam("api_key") apiKey: String = "AJGX3hUuvjKfiJMprHS6sGFOkRTJUCK7",
        @RequestParam("country") country: String,
        @RequestParam("year") year: Int
    ): List<HolidayResponse>

    @GetMapping("/holidays/{id}")
    fun getHolidayById(
        @PathVariable("id") id: String,
        @RequestParam("api_key") apiKey: String = "AJGX3hUuvjKfiJMprHS6sGFOkRTJUCK7"
    ): HolidayResponse

    @GetMapping("/upcoming")
    fun upcoming(
        @RequestParam("api_key") apiKey: String = "AJGX3hUuvjKfiJMprHS6sGFOkRTJUCK7"
    ): List<HolidayResponse>

    @GetMapping("/byDate")
    fun getHolidaysByDate(
        apiKey: String = "AJGX3hUuvjKfiJMprHS6sGFOkRTJUCK7",
        country: String,
        year: Int,
        month: Int,
        day: Int
    ): List<HolidayResponse>


}