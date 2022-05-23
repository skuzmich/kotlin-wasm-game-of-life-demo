class Canvas(canvas: JsCanvas) {
    private val context: JsCanvasContext = canvas.getContext("2d")
    val width = canvas.width
    val height = canvas.height

    fun fillRect(value: Boolean, x: Int, y: Int, width: Int, height: Int) {
        context.fillStyle = if (value) "#a97bff" else "#27282c"
        context.fillRect(x, y, width, height)
    }
}
