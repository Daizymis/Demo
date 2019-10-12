module.exports = {
    devServer: {
        port: 8090,
        proxy: {
            '/hm': {
                target: 'http://localhost:8661',
                ws: true,
                changeOrigin: true
            },
        }
    },
    configureWebpack: config => {
        require('vux-loader').merge(config, {
            options: {},
            plugins: ['vux-ui']
        })
    },
}
