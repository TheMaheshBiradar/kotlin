package lambda

/**
 * Created by Mahesh on 24-06-2018.
 */
/**
 *
 * this is nothing but first version of DSL
 */

fun dependencies(init:X.()->Unit):X{

    var x=X()
    x.init()
    return x
}

class X {

    fun  compile(s: String) {
    }

    fun  testCompile(s: String) {
    }

}

fun main(args: Array<String>) {

dependencies {
    this.compile("arrow");
    this.testCompile("junit");
}

}