package com.diegourtado.tremendapeli.interactor

class TvShowsInteractor {

    companion object {
        const val API_KEY: String = "155446d57fd2ec28616d19685b76fbfb"
        const val BASE_URL: String = "https://api.themoviedb.org/3/discover/movie?api_key="
    }

    interface onTvShowsListFetched {

    }
}