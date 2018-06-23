package movie

import java.io.File
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import java.util.stream.Collectors

/**
 * Created by Mahesh on 23-06-2018.
 */
class Movie (val imdbID:String, val title:String, val releaseDate: LocalDate, val genres:List<String>
             , val director:String, val actors:List<String>, val actress:List<String>, val duration:Long){


}

class MovieStore(var movieList:List<Movie>) {

    fun getMoviesByRelaseYear(year: Int): List<String> {
        return  movieList.filter { it-> it.releaseDate.year.equals(year) }.map { it->it.title };
    }


    fun getMoviesByGreaterTimeDuration(duration: Long): List<String> {
        return  movieList.filter { it-> it.duration>duration}.map { it->it.title };
    }


    fun getMoviesByActorName(actorName: String): List<String> {
        return  movieList.filter { it-> it.actors.contains(actorName)}.map { it->it.title };
    }


    fun getMoviesByActressName(actressName: String): List<String> {
        return  movieList.filter { it-> it.actress.contains(actressName)}.map { it->it.title };
    }

}


fun main(args: Array<String>) {
    var  movies= listOf(Movie("1","3 Idiots", LocalDate.of(2009,12,25), listOf("Comedy,Drama"),"Rajkumar Hirani",listOf("Aamir Khan","Madhavan","Sharman Joshi"),listOf("Kareena Kapoor"),170)
            ,Movie(title = "Munna Bhai M.B.B.S.",releaseDate = LocalDate.of(2003,12,19),actors = listOf("Sunil Dutt,Sanjay Dut,Arshad Warsi"),actress = listOf("Gracy Singh"),duration = 156L
            ,genres = listOf("Comedy,Drama,Musical"),imdbID = "tt0374887",director = "Rajkumar Hirani"));


    File("src/movie/movies.csv").readLines().drop(1).map { row->row.split(",") }

    val movieStore=MovieStore(movies);


    print(movieStore.getMoviesByRelaseYear(2003))

    print(movieStore.getMoviesByGreaterTimeDuration(150))
    print(movieStore.getMoviesByActorName("Aamir Khan"))
    print(movieStore.getMoviesByActressName("Gracy Singh"))


}