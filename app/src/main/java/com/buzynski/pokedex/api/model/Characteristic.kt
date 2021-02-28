package com.buzynski.pokedex.api.model

data class Characteristic(
    val descriptions: List<Description>,
    val gene_modulo: Int,
    val highest_stat: HighestStat,
    val id: Int,
    val possible_values: List<Int>
) {
    data class Description(
        val description: String,
        val language: Language
    ) {
        data class Language(
            val name: String,
            val url: String
        )
    }

    data class HighestStat(
        val name: String,
        val url: String
    )
}