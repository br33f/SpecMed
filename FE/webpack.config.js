const path = require('path');
const merge = require('webpack-merge');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const webpackCommon = {
    entry: './app/index.js',
    output: {
        filename: 'app.js',
        path: path.join(__dirname, './public'),
        publicPath: '/'
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                use: ['babel-loader'],
                exclude: /node_modules/
            },
            {
                test: /\.jsx$/,
                use: ['babel-loader'],
                exclude: /node_modules/
            },
            {
                test: /\.(scss)$/,
                use: [
                    {loader: 'style-loader'},
                    {loader: 'css-loader'},
                    {loader: 'sass-loader'}
                ]
            },
            {
                test: /\.(css)$/,
                use: [
                    {loader: 'style-loader'},
                    {loader: 'css-loader'},
                ]
            },
            {
                test: /\.(gif|png|jpe?g|svg)$/i,
                use: {
                    loader: 'file-loader',
                    options: {
                        name: "[path][name].[hash].[ext]",
                    },
                }
            },
            {
                test: /\.(woff2?|ttf|svg|eot)(\?v=\d+\.\d+\.\d+)?$/,
                use: {
                    loader: 'file-loader'
                }
            }
        ]
    },
    plugins: [new HtmlWebpackPlugin({
        template: './app/assets/app.html',
        filename: 'index.html',
        inject: 'body'
    })],
    resolve: {
        modules: [
            path.join(__dirname, './node_modules'),
            path.join(__dirname, './app')
        ]
    },
    resolveLoader: {
        modules: [
            path.join(__dirname, './node_modules')
        ]
    }
};

switch (process.env.npm_lifecycle_event) {
    case 'start':
    case 'dev':
        module.exports = merge(webpackCommon, {
            devtool: '#inline-source-map',
            devServer: {
                contentBase: path.join(__dirname, 'public'),
                compress: true,
                port: 9000,
                historyApiFallback: true
            }
        });
        break;
    default:
        module.exports = merge(webpackCommon, {
            devtool: 'source-map'
        });
        break;
}