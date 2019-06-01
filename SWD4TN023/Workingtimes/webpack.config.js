module.exports = {
  context: __dirname + "/src",
  entry: "./index.js",
  output: {
    path: __dirname + "/public",
    filename: "app.js"
  },
  module: {
    loaders: [
      {
        test: /.js/,
        loader: 'babel-loader',
        exclude: /node_modules/,
        query: {
          presets: ['es2015', 'react']
        }
      }
    ]
  },
}