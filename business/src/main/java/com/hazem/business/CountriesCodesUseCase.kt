package com.hazem.business

import java.util.*

class CountriesCodesUseCase : UseCase<Unit, Array<String>>() {

    override suspend fun execute(parameters: Unit): Array<String> {
        return Locale.getISOCountries()
    }
}