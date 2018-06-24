package basics

/**
 * Created by Mahesh on 24-06-2018.
 */

fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}

fun main(args: Array<String>) {

    var mahesh:String?=null;

    val s = mahesh?: fail("Name required")
    println(s)
}
