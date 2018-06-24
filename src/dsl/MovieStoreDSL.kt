package dsl

import movie.Movie1
import movie.MovieStore
import movie.MovieStore1

/**
 * Created by Mahesh on 24-06-2018.
 */


fun movieStore(init:MovieStore1.()->Unit):MovieStore1{

    var store=MovieStore1()
    store.init();
    return store;



}

fun main(args: Array<String>) {

    var store=movieStore {
        this.movie{
            name = "3 Idiots"
           releaseDate=java.time.LocalDate.now()
           actors=kotlin.collections.listOf<String>("aamir")
        }
        this.movie{
            name = "3 Idiots"
            releaseDate=java.time.LocalDate.now()
            actors=kotlin.collections.listOf<String>("aamir")
        }
    }

    print(store.movieList)
}

