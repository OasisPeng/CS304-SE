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
        target: 'http://120.78.7.40:8090',
        changeOrigin: true,
        pathRewrite: {
          '^/api': '',
        },
      },
    },
  },

}