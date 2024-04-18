package org.hse.template.client.rest.model

data class HolidayResponse(
    val meta: Meta,
    val response: Response
)

data class Meta(
    val code: Int
)

data class Response(
    val holidays: List<Holiday>
)

data class Holiday(
    val name: String,
    val description: String,
    val date: HolidayDate,
    val type: List<String>
)

data class HolidayDate(
    val iso: String
)
