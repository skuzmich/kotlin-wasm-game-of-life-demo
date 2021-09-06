// noinspection JSUnresolvedVariable

config.devServer.open = {
    app: {
        name: 'google chrome',
        arguments: [
            '--js-flags=--experimental-wasm-typed-funcref --experimental-wasm-gc',
        ],
    }
}

config.devServer.static.push(
    __dirname + "/kotlin/"
)

config.output = {}