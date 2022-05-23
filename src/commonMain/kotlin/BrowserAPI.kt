external interface Document {
    fun querySelector(query: String): Node
}

external class Node

external val document: Document

external fun setInterval(f: () -> Unit, i: Int)

external interface JsCanvas {
    val width: Int
    val height: Int

    fun getContext(contextType: String): JsCanvasContext
}

external interface JsCanvasContext {
    var fillStyle: String
    fun fillRect(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
    )
}
