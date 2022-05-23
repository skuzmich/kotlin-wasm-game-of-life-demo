class GameOfLife(
    private val canvas: Canvas,
    private val cellSizeInPixels: Int
) {
    private val model: GameOfLifeModel = GameOfLifeModel(
        canvas.width / cellSizeInPixels,
        canvas.height / cellSizeInPixels
    )

    fun initialize() {
        fillGridWithGliderGuns(model.grid)
        drawOnCanvas(incremental = false)
    }

    fun step() {
        model.gameStep()
        drawOnCanvas(incremental = true)
    }

    private fun drawOnCanvas(incremental: Boolean) {
        model.grid.forEachIndexed { x, y, value ->
            if (!incremental || model.nextGrid[x, y] != value) {
                canvas.fillRect(
                    value,
                    x * cellSizeInPixels,
                    y * cellSizeInPixels,
                    cellSizeInPixels,
                    cellSizeInPixels
                )
            }
        }
    }
}

const val gliderGun =
    """........................O
......................O.O
............OO......OO............OO
...........O...O....OO............OO
OO........O.....O...OO
OO........O...O.OO....O.O
..........O.....O.......O
...........O...O
............OO"""


fun fillGridWithGliderGuns(grid: BooleanGrid, gunOffset: Int = 40) {
    repeat (grid.sizeX / gunOffset) { i ->
        grid.fillFromString(gliderGun, offsetX = i * gunOffset)
    }
    repeat(grid.sizeY / gunOffset) { i ->
        grid.fillFromString(gliderGun, offsetY = i * gunOffset)
    }
}
