module.exports = {
  devServer: {
    https: false,
    port: 8083,
    open: true,
    proxy: {
      "/api/v1": {
        target: "http://localhost:8080",
        changeOrigin: true,
        logLevel: "debug",
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
