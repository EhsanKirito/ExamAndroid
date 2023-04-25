package com.example.kointest

class NetworkParams {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/popular/"
        const val API_KEY = "a447989f2b34e1193b1194c6265c3d3f"
        var page = "1"


        private fun getBaseOptions(): Map<String, String> {
            val optionsHashMap = HashMap<String, String>()
            optionsHashMap["api_key"] = API_KEY
            optionsHashMap["language"] = "en-Us"
            optionsHashMap["page"] = page
            return optionsHashMap
        }

        fun getPopularOptions(page:Int): Map<String, String> {
            val popularOptions = HashMap<String, String>()
            popularOptions.putAll(getBaseOptions())
            popularOptions["page"] = page.toString()
            return popularOptions
        }
    }
}