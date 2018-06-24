package omdbapi

import kotlinx.coroutines.experimental.Deferred
import movie.Movie
import movie.MovieStore
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import java.net.URL
import java.util.*

/**
 * Created by Mahesh on 24-06-2018.
 */
fun getMovie(imdbId:String):JSONObject{

    val url = URL("http://omdbapi.com/?i="+imdbId+"&apikey=df5e889")
    val result=url.openStream().bufferedReader().readLine();
    val parser=JSONParser()

    return parser.parse(result.toString()) as JSONObject

}

fun main(args: Array<String>) {

    val mutableList: MutableList<Movie> = ArrayList()

    val startTime = System.currentTimeMillis();

    var moviesList = File("src/movie/movies.csv").readLines(Charsets.US_ASCII).drop(1).dropLast(1);



    for (movie in moviesList) {
        val movieObject = Movie.createMovie(movie)
        val result = getMovie(movieObject.imdbID)
        println(result.get("Plot"))
        mutableList.add(movieObject)

    }
    val longTime = System.currentTimeMillis();

    print("time taken ${longTime - startTime}")

}