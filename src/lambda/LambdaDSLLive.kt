package lambda

/**
 * Created by Mahesh on 24-06-2018.
 */
/**
 *
 * this is nothing but first version of DSL
 */

fun buildString(action:(StringBuilder)->Unit):String{
    val sb= StringBuilder()
    action.invoke(sb)
    return sb.toString()
}


/**
 *
 * action:StringBuilder.() means action:()-> it is still lambda but with receiver now as
 *
 * StringBuilder.() means this is available already as extension function has this available so
 * look how it is being called
 */

fun buildStringLambdaWithReceiver(action:StringBuilder.()->Unit):String{
    val sb= StringBuilder()
    action.invoke(sb)
    return sb.toString()
}

fun main(args: Array<String>) {
    buildString { it:StringBuilder->it.append("Hello").append("Mahesh") }
    buildString {it.append("Hello").append(" Mahesh") }
    /**
     * below example does not need it and this is implicit
     * in buildStringLambdaWithReceiver because it is lambda with receiver :)
     *
     */
   println(buildStringLambdaWithReceiver { append("HI").append("Mahesh") })
}