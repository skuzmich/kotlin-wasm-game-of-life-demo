class Canvas(canvas: JsCanvas) {
    private val context: JsCanvasContext = getCanvas2dContext(canvas)
    val width = getWidth(canvas)
    val height = getHeight(canvas)

    fun fillRect(value: Boolean, x: Int, y: Int, width: Int, height: Int) {
        setContextFillStyle(context, value)
        contextFillRect(context, x, y, width, height)
    }
}

external interface JsCanvas
external interface JsCanvasContext

external fun getCanvas2dContext(canvas: JsCanvas): JsCanvasContext
external fun getWidth(canvas: JsCanvas): Int
external fun getHeight(canvas: JsCanvas): Int
external fun setContextFillStyle(context: JsCanvasContext, value: Boolean)
external fun contextFillRect(
    context: JsCanvasContext,
    x: Int,
    y: Int,
    width: Int,
    height: Int,
)