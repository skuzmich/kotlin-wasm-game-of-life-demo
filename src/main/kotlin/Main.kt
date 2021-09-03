var initialized = false

const val cellSize = 4
const val gridSize = 1000
const val numCells = gridSize / cellSize

var gameField = BooleanArray(numCells * numCells)
var nextGameField = BooleanArray(numCells * numCells)

fun benchmark() {
    val warmupIters = 0
    val benchmarkIters = 100
    val numRuns = 0

    repeat(warmupIters) { gameStep() }
    repeat(numRuns) {
        val startTime = performanceNow()
        repeat(benchmarkIters) { gameStep() }
        val endTime = performanceNow()
        println("Run #$it: ${endTime - startTime}")
    }
}

val gliderGun = arrayOf(
    "........................O",
    "......................O.O",
    "............OO......OO............OO",
    "...........O...O....OO............OO",
    "OO........O.....O...OO",
    "OO........O...O.OO....O.O",
    "..........O.....O.......O",
    "...........O...O",
    "............OO"
)

val gliderGuns: String = run {
    val numGunsX = 6
    val numGunsY = 6

    var result = ""

    val gunMaxLen = gliderGun.maxOf { it.length }
    for (row in gliderGun) {
        repeat(numGunsX) {
            result += row
            repeat(gunMaxLen - row.length + 2) {
                result += "."
            }
        }
        result += "\n"
    }

    repeat(numGunsY) {
        repeat(28) { result += "\n" }
        for (x in gliderGun) {
            result += x + "\n"
        }
    }
    result
}


fun main() {
    if (!initialized) {
        setUp()
        gameField.initGameField(gliderGuns)
        initialized = true
        //benchmark()
        gameField.drawGameField()
    }
    gameStep()
}


fun BooleanArray.setFieldElement(x: Int, y: Int, value: Boolean) {
    this[y * numCells + x] = value
}

fun BooleanArray.getFieldElement(x: Int, y: Int): Boolean {
    if (x < 0) return false
    if (y < 0) return false
    if (x >= numCells) return false
    if (y >= numCells) return false
    return this[y * numCells + x]
}

fun BooleanArray.drawGameField() {
    for (i in 0 until numCells) {
        for (j in 0 until numCells) {
            val res = getFieldElement(i, j)
            fill(res, i * cellSize, j * cellSize, cellSize, cellSize)
        }
    }
}

fun BooleanArray.initGameField(text: String) {
    var i = 0
    var j = 0
    for (c in text) {
        if (c == '\n') {
            j++
            i = 0
            continue
        }

        val dead = c != '.'
        setFieldElement(i, j, dead)
        i++
    }
}

fun gameStep() {
    for (i in 0 until numCells) {
        for (j in 0 until numCells) {
            var numAliveAdjecent = 0

            if (gameField.getFieldElement(i - 1,j - 1)) numAliveAdjecent++
            if (gameField.getFieldElement(i - 1,j)) numAliveAdjecent++
            if (gameField.getFieldElement(i - 1,j + 1)) numAliveAdjecent++
            if (gameField.getFieldElement(i, j - 1)) numAliveAdjecent++
            if (gameField.getFieldElement(i, j + 1)) numAliveAdjecent++
            if (gameField.getFieldElement(i + 1, j - 1)) numAliveAdjecent++
            if (gameField.getFieldElement(i + 1, j)) numAliveAdjecent++
            if (gameField.getFieldElement(i + 1, j + 1)) numAliveAdjecent++

            val isCurrentAlive = gameField.getFieldElement(i, j)

            val result = when {
                isCurrentAlive && (numAliveAdjecent == 2 || numAliveAdjecent == 3) -> true
                !isCurrentAlive && numAliveAdjecent == 3 -> true
                else -> false
            }

            nextGameField.setFieldElement(i, j, result)
            if (result != isCurrentAlive) {
                fill(result, i * cellSize, j * cellSize, cellSize, cellSize)
            }
        }
    }
    nextGameField = gameField.also { gameField = nextGameField }
}

//annotation class JsFun(val x: String)

@JsFun("""
    () => {
        window.setInterval(() => {
            wasmInstance.exports.main();
        }, 0);
    }
""")
fun setUp() {
//    window.setInterval({
//        main()
//    }, 0)
}

@JsFun("() => { return performance.now(); }")
fun performanceNow(): Double =
    TODO()
//    js("performance.now()")

@JsFun("""
    (isAlive, x, y, width, height) => {
         ctx.fillStyle = isAlive ? 'white' : 'black';
         ctx.fillRect(x, y, width, height);
    }
""")
fun fill(isAlive: Boolean, x: Int, y: Int, width: Int, height: Int) {
//    js("""
//         ctx.fillStyle = isAlive ? 'white' : 'black';
//         ctx.fillRect(x, y, width, height);
//""")
}