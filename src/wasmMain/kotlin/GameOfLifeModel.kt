class GameOfLifeModel(
    private val sizeX: Int,
    private val sizeY: Int
) {

    var grid = BooleanGrid(sizeX, sizeY)
    var nextGrid = BooleanGrid(sizeX, sizeY)

    private fun getCurrentState(x: Int, y: Int): Boolean {
        if (x < 0) return false
        if (y < 0) return false
        if (x >= sizeX) return false
        if (y >= sizeY) return false
        return grid[x, y]
    }

    private fun countAliveNeighbours(x: Int, y: Int): Int {
        var result = 0
        if (getCurrentState(x - 1, y - 1)) result++
        if (getCurrentState(x - 1, y)) result++
        if (getCurrentState(x - 1, y + 1)) result++
        if (getCurrentState(x, y - 1)) result++
        if (getCurrentState(x, y + 1)) result++
        if (getCurrentState(x + 1, y - 1)) result++
        if (getCurrentState(x + 1, y)) result++
        if (getCurrentState(x + 1, y + 1)) result++
        return result
    }

    fun gameStep() {
        grid.forEachIndexed { x, y, currentState ->
            val aliveNeighbours = countAliveNeighbours(x, y)

            nextGrid[x, y] = when {
                currentState && (aliveNeighbours == 2 || aliveNeighbours == 3) ->
                    true

                !currentState && aliveNeighbours == 3 ->
                    true

                else ->
                    false
            }
        }

        // Swap next and current grid
        nextGrid = grid.also {
            grid = nextGrid
        }
    }
}