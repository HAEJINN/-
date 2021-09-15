module.exports = {
  devServer: {
    https: false,
    port: 8083,
    open: true,
    proxy: {
      "/api": {
        target: "http://localhost:8080/",
      },
    },
    historyApiFallback: true,
    hot: true,
  },
  pluginOptions: {
    quasar: {
      importStrategy: "kebab",
      rtlSupport: false,
    },
  },
  transpileDependencies: ["quasar"],
};
