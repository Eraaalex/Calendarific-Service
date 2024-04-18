package org.hse.template.controller

import org.hse.template.client.rest.api.CalendarificClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class CalendarificController(
    private val calendarificClient: CalendarificClient
) {
    @GetMapping("/holidays")
    fun getHolidays(@RequestParam amount: Int) = calendarificClient.getHolidays(country = "RU", year = 2021)

    @GetMapping("/{id}")
    fun getHolidayDetail(@PathVariable id: String) =
        calendarificClient.getHolidayById(id = id)

    @GetMapping("/byDate")
    fun getHolidaysByDate(
        @RequestParam country: String,
        @RequestParam year: Int,
        @RequestParam month: Int,
        @RequestParam day: Int
    ) = calendarificClient.getHolidaysByDate(
        country = country,
        year = year,
        month = month,
        day = day
    )


}
