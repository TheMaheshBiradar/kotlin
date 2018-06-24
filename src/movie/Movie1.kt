package movie

import java.io.File
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.stream.Collectors

/**
 * Created by Mahesh on 23-06-2018.
 */



class Movie1 (var name:String="",
              var releaseDate: LocalDate= LocalDate.now(),
              var actors:List<String> = listOf()) {


    companion object factory {

        fun createMovie1(name: String, actors: List<String>, releaseDate: String): Movie1 {
            return Movie1(name = name,
                    releaseDate = LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                    actors = actors)
        }

    }


}

public class MovieStore1() {

    var movieList:MutableList<Movie1> = mutableListOf()

    fun  movie(init:Movie1.()->Unit):Unit {
        var movie=Movie1()
        movie.init()
        movieList.add(movie)

    }

    override fun toString(): String {
        return movieList.toString();
    }

}

