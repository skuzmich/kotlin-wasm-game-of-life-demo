@file:Suppress("NON_EXPORTABLE_TYPE", "EXPERIMENTAL_IS_NOT_ENABLED")
@file:OptIn(ExperimentalJsExport::class)

@JsExport
fun createGameOfLife(canvas: JsCanvas): GameOfLife {
    val gameOfLife = GameOfLife(Canvas(canvas), cellSizeInPixels = 8)
    gameOfLife.initialize()
    return gameOfLife
}

@JsExport
fun stepGameOfLife(gameOfLife: GameOfLife) {
    gameOfLife.step()
}