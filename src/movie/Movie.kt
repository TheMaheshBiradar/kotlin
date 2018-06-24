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


enum class Genres() {
    Comedy,
    Drama,
    Action,
    Musical,
    Family,
    Adventure,
    History,
    Romance;
}

class Movie private constructor(val imdbID:String, val title:String, val releaseDate: LocalDate, val genres:List<Genres>
             , val director:String, val actors:List<String>, val actress:List<String>, val duration:Long){



    companion object factory{

        fun createMovie(record:String) :Movie{
         val   movieAttributes=record.split(",")
           return Movie(title = movieAttributes[0],
                releaseDate = LocalDate.parse(movieAttributes[1], DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                actors = movieAttributes[2].split("|").toList(),
                actress = movieAttributes[3].split("|").toList()
                ,duration = movieAttributes[4].toLong(),
                genres = movieAttributes[5].split("|").map {Genres.valueOf(value =it)},
                imdbID = movieAttributes[6],
                director = movieAttributes[7])
                }
    }

    /**
     * destructing is not required when data class is present because data classes are meant for that :)
     */
    operator fun component1()=title
    operator fun component2()=director

    //above access should be (title,director)=movie...close to javascript
    //order has to be same.... or else use _ if you don't want to use it

    //operator overloadinig contaiins

  //below code can be invoked using movie in movies in movie stor
    /*operator fun contains(movie: Movie):Boolean{
        return true;
    }
*/

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

    /**
     * map(it->it.title and it.title is one and the same)
     */
    fun getMoviesByActressName(actressName: String): List<String> {
      //  return  movieList.filter { it-> it.actress.contains(actressName)}.map { it->it.title };
        return  movieList.filter { it-> it.actress.contains(actressName)}.map {it.title };
    }

    /**
     * this function will take any criteria to filter and will return the results.
     *
     * Please note we are passing lambda inside function and JVM calls it Higher order functions
     *
     */
    fun filterOnCondition (criteria:(Movie)->Boolean):List<String>{
        return movieList.filter(criteria).map { it->it.title}
    }

}


fun main(args: Array<String>) {

 /*   var  movies= listOf(Movie("1","3 Idiots", LocalDate.of(2009,12,25), listOf("Comedy,Drama"),"Rajkumar Hirani",listOf("Aamir Khan","Madhavan","Sharman Joshi"),listOf("Kareena Kapoor"),170)
            ,Movie(title = "Munna Bhai M.B.B.S.",releaseDate = LocalDate.of(2003,12,19),actors = listOf("Sunil Dutt,Sanjay Dut,Arshad Warsi"),actress = listOf("Gracy Singh"),duration = 156L
            ,genres = listOf("Comedy,Drama,Musical"),imdbID = "tt0374887",director = "Rajkumar Hirani"));
*/
    val mutableList : MutableList<Movie> = ArrayList()


    var moviesList=File("src/movie/movies.csv").readLines(Charsets.US_ASCII).drop(1).dropLast(1);

    for(movie in moviesList){
  /*          val movieAttributes=movie.split(",");
            val movieObject=Movie(title = movieAttributes[0],
                releaseDate = LocalDate.parse(movieAttributes[1], DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                actors = movieAttributes[2].split("|").toList(),
                actress = movieAttributes[3].split("|").toList()
                ,duration = movieAttributes[4].toLong(),
                genres = movieAttributes[5].split("|").toList(),
                imdbID = movieAttributes[6],
                director = movieAttributes[7])
  */

        val movieObject =Movie.createMovie(movie)
        mutableList.add(movieObject)

        }



    //val movieStore=MovieStore(movies);
    val movieStore=MovieStore(mutableList);

    println(movieStore.getMoviesByRelaseYear(2003))

    println(movieStore.getMoviesByGreaterTimeDuration(150))
    println(movieStore.getMoviesByActorName("Aamir Khan"))
    println(movieStore.getMoviesByActressName("Gracy Singh"))

    println(movieStore.filterOnCondition { it.releaseDate.year==2003});
}