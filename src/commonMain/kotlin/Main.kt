fun main() {
    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
    val canvas = document.querySelector("#game") as JsCanvas
    val gameOfLife = createGameOfLife(canvas)
    setInterval({
        stepGameOfLife(gameOfLife)
    }, 10)
}
