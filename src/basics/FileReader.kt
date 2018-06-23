package main.kotlin

import java.io.File
import java.util.stream.Collector
import java.util.stream.Collectors
import java.util.HashMap
import jdk.nashorn.internal.objects.NativeArray.forEach



/**
 * Created by Mahesh on 23-06-2018.
 */

fun main(array:Array<String>){

    var lines= File("src/basics/word-count.txt").readLines()

    val frequencyMap = HashMap<String, Int>()


    lines.map { line ->
        line.split(" ").forEach { word ->
            if (frequencyMap.contains(word)) {
                frequencyMap.put(word, frequencyMap.get(word)!!.plus(1))

            } else {
                frequencyMap.put(word, 1)

            }

        }
    }

    print(frequencyMap)

    /*BETTER SOLUTION*/

    lines.flatMap { it.split(" ") }.groupBy { it }.forEach { println("${it.key} = ${it.value.size}") }
}