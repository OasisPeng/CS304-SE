module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  lintOnSave: false,
  devServer: {
    open: true,
    port: 8080,
    proxy: {
      '/api': {
        // target: 'http://localhost:8090',
        target: 'http://8.138.27.126:8090',
        changeOrigin: true,
        pathRewrite: {
          '^/api': '',
        },
      },
    },
  },

}