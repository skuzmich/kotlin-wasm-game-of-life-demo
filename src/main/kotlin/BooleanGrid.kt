class BooleanGrid(
    val sizeX: Int,
    val sizeY: Int
) {
    private val storage: BooleanArray =
        BooleanArray(sizeX * sizeY)

    private fun storageIndex(x: Int, y: Int): Int =
        y * sizeY + x

    operator fun set(x: Int, y: Int, value: Boolean) {
        storage[storageIndex(x, y)] = value
    }

    operator fun get(x: Int, y: Int): Boolean {
        return storage[storageIndex(x, y)]
    }

    // TODO: Comment with examples
    fun fillFromString(
        text: String,
        offsetX: Int = 0,
        offsetY: Int = 0,
    ) {
        var x = offsetX
        var y = offsetY
        for (c in text) {
            if (c == '\n') {
                y++
                x = offsetX
                continue
            }

            this[x, y] = c != '.'
            x++
        }
    }
}

inline fun BooleanGrid.forEachIndexed(f: (Int, Int, Boolean) -> Unit) {
    for (x in 0 until sizeX) {
        for (y in 0 until sizeY) {
            f(x, y, this[x, y])
        }
    }
}
