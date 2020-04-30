[TOC]

# node js 中主要包含两方面

## 1、vue 相关的页面

​	使用vuex，参考：https://cn.vuejs.org/  https://vuex.vuejs.org/
​	其中表格未使用Vue实现，使用的是一个基于JQuery的插件bootraptable
​	参考：https://bootstrap-table.com/

## 2、node 实现的后台 使用的框架是Soar

​	Soar 是基于Sails的MVC框架，几乎和Sails完全一样，可参考
​	http://sailsdoc.swift.ren/
​	https://linxiaowu66.gitbooks.io/sailsjs/
​	https://github.com/balderdashy/sails

## 3、安装部署

### 1、nodeJs工具安装

- 下载地址：[http://nodejs.cn/](https://links.jianshu.com/go?to=http%3A%2F%2Fnodejs.cn%2F)；windows一路next安装完成即可。

- https://code.visualstudio.com/下载VSCode

- 下载Git，https://git-scm.com/

- VSCode修改命令工具为Git-Bash

  - 点击左下角的齿轮选择设置(或者通过快捷键:Ctrl+,直接打开)：

    <img src="https://raw.githubusercontent.com/stephenZkang/learn/master/img/node/image-20200203094914129.png" alt="image-20200203094914129" style="zoom:50%;" />

  - 在设置的输入框搜索：Shell:windows；找到：Terminal>Integrated>Shell:windows，这一项点击在settings.json中编辑。

  - settings.json将内容修改为："terminal.integrated.shell.windows": "C:\tool\Git\bin\bash.exe"，其中C:\tool\Git是我Git的安装路径。

  - 重启编辑器通过快捷键：Ctrl+~ 打开命令工具， 显示如下图既OK。

- 安装vue-cli

  - 为了加快安装效率需要将nodejs的默认镜像库修改为国内的淘宝镜像库(npm.taobao.org)。
    在VSCode中通过命令工具进入nodejs安装目录；

    <img src="https://raw.githubusercontent.com/stephenZkang/learn/master/img/node/image-20200203095221335.png" alt="image-20200203095221335" style="zoom:50%;" />

  - 输入命令：npm config set registry [https://registry.npm.taobao.org](https://links.jianshu.com/go?to=https%3A%2F%2Fregistry.npm.taobao.org) 修改镜像库地址；

  - 通过命令：npm install -g vue-cli 安装vue-cli,安装完成之后通过命令:vue --version 查看版本号，检查vue-cli是否安装正常

    ![image-20200203095456649](https://raw.githubusercontent.com/stephenZkang/learn/master/img/node/image-20200203095456649.png)

  - 

### 2、构建项目

- 进入存放项目的文件夹，我这里把项目放在d盘的nodejs文件夹下的html-project，则输入命令cd /d/nodejs/html-project

  <img src="https://raw.githubusercontent.com/stephenZkang/learn/master/img/node/image-20200203095806012.png" alt="image-20200203095806012" style="zoom:50%;" />

- 运行命令：vue init webpack observer 这里的observer是项目名称可以自己定义

- 选择项目属性；

  ```js
   Project name:——项目名称
   Project description:——项目描述
   Author:——作者
   Vue build:——构建模式，一般默认选择第一种
   Install vue-router?:——是否安装引入vue-router，这里选是，vue-router是路由组件,后面构建项目会用到
   Use ESLint to lint your code?:——这里强烈建议选no 否则你会非常痛苦，eslint的格式验证非常严格，多一个空格少一个空格都会报错，所以对于新手来说，一般不建议开启，会加大开发难度
   Setup unit tests with Karma + Mocha 以及Setup e2e tests with Nightwatch这两个是测试，可以不用安装
   should we run 'npm install' for you after the project has been created?——选择npm
  ```

- 运行项目，添加UI库

  - 在项目存放目录运行命令：code . 打开项目，我这里的目录是：/d/nodejs/html-project/observer ;运行完成后VSCode会新开一个窗口，就是刚刚构建的项目。

    <img src="https://raw.githubusercontent.com/stephenZkang/learn/master/img/node/image-20200203100335205.png" alt="image-20200203100335205" style="zoom:50%;" />

  - 在项目窗口通过(刚刚的新窗口)快捷键：Ctrl+~ 打开gitbash终端。

  - 这里用VUX UI库举例(官网地址:[https://vux.li/](https://links.jianshu.com/go?to=https%3A%2F%2Fvux.li%2F))，运行命令：npm install vux --save 安装vux UI库。

    <img src="https://raw.githubusercontent.com/stephenZkang/learn/master/img/node/image-20200203100602158.png" alt="image-20200203100602158" style="zoom:50%;" />

  - 运行命令：npm run dev 启动项目

    ![image-20200203100730962](https://raw.githubusercontent.com/stephenZkang/learn/master/img/node/image-20200203100730962.png)

  - 打开浏览区输入访问地址：http://localhost:8080，可以访问则说明项目构建成功。

    - - 

    ![image-20200203100845382](https://raw.githubusercontent.com/stephenZkang/learn/master/img/node/image-20200203100845382.png)

  - https://element.eleme.cn/#/zh-CN基于vue的组件

  - vue项目结构详解

    ```js
    ​```html
    |-- build                            // 项目构建(webpack)相关代码
    |   |-- build.js                     // 生产环境构建代码
    |   |-- check-version.js             // 检查node、npm等版本
    |   |-- dev-client.js                // 热重载相关
    |   |-- dev-server.js                // 构建本地服务器
    |   |-- utils.js                     // 构建工具相关
    |   |-- webpack.base.conf.js         // webpack基础配置
    |   |-- webpack.dev.conf.js          // webpack开发环境配置
    |   |-- webpack.prod.conf.js         // webpack生产环境配置
    |-- config                           // 项目开发环境配置
    |   |-- dev.env.js                   // 开发环境变量
    |   |-- index.js                     // 项目一些配置变量
    |   |-- prod.env.js                  // 生产环境变量
    |   |-- test.env.js                  // 测试环境变量
    |-- src                              // 源码目录
    |   |-- components                   // vue公共组件
    |   |-- store                        // vuex的状态管理
    |   |-- App.vue                      // 页面入口文件
    |   |-- main.js                      // 程序入口文件，加载各种公共组件
    |-- static                           // 静态文件，比如一些图片，json数据等
    |   |-- data                         // 群聊分析得到的数据用于数据可视化
    |-- .babelrc                         // ES6语法编译配置
    |-- .editorconfig                    // 定义代码格式
    |-- .gitignore                       // git上传需要忽略的文件格式
    |-- README.md                        // 项目说明
    |-- favicon.ico 
    |-- index.html                       // 入口页面
    |-- package.json                     // 项目基本信息
    ​```
    
    1. vue-cli项目结构配置文件详细描述
    
      package.json
    
      ```json
      这里包含开发运行，项目打包，单元测试等命令，测试的东西先放一边，看dev和build命令。运行”npm run dev”的时候执行的是build/dev-server.js文件，运行”npm run build”的时候执行的是build/build.js文件，我们可以从这两个文件开始进行代码阅读分析。
      
      "scripts": {
      "dev": "webpack-dev-server --inline --progress --config build/webpack.dev.conf.js",
      "start": "npm run dev",
      "lint": "eslint --ext .js,.vue src",
      "build": "node build/build.js"
      },
      
    ```
    
    2. build文件夹分析
    
      build/build.js
    
      ```js
      
      // 这里包含了：
      // loading动画；
      // 删除目标文件夹；
      // 执行webpack构建；
      // 输出信息；
      // webpack编译之后会输出到配置里面指定的目标文件夹；删除目标文件夹之后再创建是为了// 去除旧的内容，以免产生不可预测的影响。
      
      'use strict'
      // 检查NodeJS和npm的版本。
      require('./check-versions')()
      
      process.env.NODE_ENV = 'production'
      
      // ora插件，实现node.js命令行环境的loading效果和显示各种状态的图标等。
      const ora = require('ora')
      const rm = require('rimraf')
      const path = require('path')
      // 用于在控制台输出带颜色字体的插件。
      const chalk = require('chalk')
      const webpack = require('webpack')
      const config = require('../config')
      const webpackConfig = require('./webpack.prod.conf')
      
      const spinner = ora('building for production...')
      spinner.start()
      
      // rimraf插件，每次启动编译或者打包之前，先把整个dist文件夹删除，然后再重新生成dist。
      rm(path.join(config.build.assetsRoot, config.build.assetsSubDirectory), err => {
      if (err) throw err
      webpack(webpackConfig, (err, stats) => {
          spinner.stop()
          if (err) throw err
          process.stdout.write(stats.toString({
          colors: true,
          modules: false,
          children: false, // If you are using ts-loader, setting this to true will make TypeScript errors show up during build.
          chunks: false,
          chunkModules: false
          }) + '\n\n')
      
          if (stats.hasErrors()) {
          console.log(chalk.red('  Build failed with errors.\n'))
          process.exit(1)
          }
      
          console.log(chalk.cyan('  Build complete.\n'))
          console.log(chalk.yellow(
          '  Tip: built files are meant to be served over an HTTP server.\n' +
          '  Opening index.html over file:// won\'t work.\n'
          ))
      })
      })
      
      ```
    
      bulid/check-version.js
    
      ```js
      
      if (shell.which('npm')) {
      versionRequirements.push({
          name: 'npm',
          currentVersion: exec('npm --version'),
          versionRequirement: packageConfig.engines.npm
      })
      }
      
      module.exports = function () {
      const warnings = []
      // 依次判断版本是否符合要求。
      for (let i = 0; i < versionRequirements.length; i++) {
          const mod = versionRequirements[i]
      
          if (!semver.satisfies(mod.currentVersion, mod.versionRequirement)) {
          warnings.push(mod.name + ': ' +
              chalk.red(mod.currentVersion) + ' should be ' +
              chalk.green(mod.versionRequirement)
          )
          }
      }
      // 如果有警告则将其输出到控制台。
      if (warnings.length) {
          console.log('')
          console.log(chalk.yellow('To use this template, you must update following to modules:'))
          console.log()
      
          for (let i = 0; i < warnings.length; i++) {
          const warning = warnings[i]
          console.log('  ' + warning)
          }
      
          console.log()
          process.exit(1)
      }
      }
      ```
      ```
    
      build/utils.js
    
      ```jso
      这里提供工具函数，包括生成处理各种样式语言的loader，获取资源文件存放路径的工具函数。
      配置静态资源路径；
      生成cssLoaders用于加载.vue文件中的样式；
      生成styleLoaders用于加载不在.vue文件中的单独存在的样式文件。
      ```
    
      build/vue-loader.conf.js
    
      ```js
      // 这里负责处理.vue文件中的样式，配置css加载器以及编译css之后自动添加前缀。
      
      'use strict'
      const utils = require('./utils')
      const config = require('../config')
      const isProduction = process.env.NODE_ENV === 'production'
      const sourceMapEnabled = isProduction
      ? config.build.productionSourceMap
      : config.dev.cssSourceMap
      
      module.exports = {
      // css加载器。
      loaders: utils.cssLoaders({
          sourceMap: sourceMapEnabled,
          extract: isProduction
      }),
      cssSourceMap: sourceMapEnabled,
      cacheBusting: config.dev.cacheBusting,
      // 让vue-loader知道需要对video的src属性的内容转换为模块。
      transformToRequire: {
          video: ['src', 'poster'],
          source: 'src',
          img: 'src',
          image: 'xlink:href'
      }
      }
      ```
      ```
    
      build/webpack.base.conf.js
    
      ```js
      这里这个配置里面只配置了.js、.vue、图片、字体等几类文件的处理规则，如果需要处理其他文件可以在module.rules里面另行配置。从代码中看到，dev-server使用的webpack配置来自build/webpack.dev.conf.js文件（测试环境下使用的是build/webpack.prod.conf.js，这里暂时不考虑测试环境）。而build/webpack.dev.conf.js中又引用了webpack.base.conf.js，所以这里先看webpack.base.conf.js。
      webpack.base.conf.js主要完成下面的操作：
      配置webpack编译入口；
      配置webpack输出路径和命名规则；
      配置模块resolve规则；
      配置不同类型模块的处理规则。
      
      'use strict'
      const path = require('path')
      const utils = require('./utils')
      const config = require('../config')
      const vueLoaderConfig = require('./vue-loader.conf')
      // 给出正确的绝对路径。
      function resolve (dir) {
      return path.join(__dirname, '..', dir)
      }
      
      const createLintingRule = () => ({
      test: /\.(js|vue)$/,
      loader: 'eslint-loader',
      enforce: 'pre',
      include: [resolve('src'), resolve('test')],
      options: {
          formatter: require('eslint-friendly-formatter'),
          emitWarning: !config.dev.showEslintErrorsInOverlay
      }
      })
      
      module.exports = {
      context: path.resolve(__dirname, '../'),
      entry: {
          // 配置webpack编译入口。
          app: './src/main.js'
      },
      // 配置webpack输出路径和命名规则。
      output: {
          // webpack输出的目标文件夹路径（例如：/dist）
          path: config.build.assetsRoot,
          // webpack输出bundle文件命名格式。
          filename: '[name].js',
          // webpack编译输出的发布路径。
          publicPath: process.env.NODE_ENV === 'production'
          ? config.build.assetsPublicPath
          : config.dev.assetsPublicPath
      },
      // 配置模块resolve的规则。
      resolve: {
          // 自动resolve的扩展名。
          extensions: ['.js', '.vue', '.json'],
          // 创建路径别名，有了别名之后引用模块更方便，例如：import Vue from 'vue/dist/vue.esm.js'可以写成 import Vue from 'vue'。
          alias: {
          'vue$': 'vue/dist/vue.esm.js',
          '@': resolve('src'),
          }
      },
      // 配置不同类型模块的处理规则。
      module: {
          rules: [
          ...(config.dev.useEslint ? [createLintingRule()] : []),
          // 对所有.vue文件使用vue-loader
          {
              test: /\.vue$/,
              loader: 'vue-loader',
              options: vueLoaderConfig
          },
          // 对src和test文件夹下的.js文件使用babel-loader。
          {
              test: /\.js$/,
              loader: 'babel-loader',
              include: [resolve('src'), resolve('test'), resolve('node_modules/webpack-dev-server/client')]
          },
          // 对图片资源文件使用url-loader，name指明了输出的命名规则。
          {
              test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
              loader: 'url-loader',
              options: {
              limit: 10000,
              name: utils.assetsPath('img/[name].[hash:7].[ext]')
              }
          },
          // 对媒体资源文件使用url-loader，name指明了输出的命名规则。
          {
              test: /\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
              loader: 'url-loader',
              options: {
              limit: 10000,
              name: utils.assetsPath('media/[name].[hash:7].[ext]')
              }
          },
          // 对字体资源文件使用url-loader，name指明了输出的命名规则。
          {
              test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
              loader: 'url-loader',
              options: {
              limit: 10000,
              name: utils.assetsPath('fonts/[name].[hash:7].[ext]')
              }
          }
          ]
      },
      node: {
          // prevent webpack from injecting useless setImmediate polyfill because Vue
          // source contains it (although only uses it if it's native).
          setImmediate: false,
          // prevent webpack from injecting mocks to Node native modules
          // that does not make sense for the client
          dgram: 'empty',
          fs: 'empty',
          net: 'empty',
          tls: 'empty',
          child_process: 'empty'
      }
      }
      ```
    
      build/webpack.dev.conf.js
    
      ```js
      这里面在webpack.base.conf的基础上增加完善了开发环境下面的配置，主要完成下面操作：
      将webpack的热重载客户端代码添加到每个entry对应的应用；
      合并基础的webpack配置；
      配置样式文件的处理规则，styleLoaders；
      配置Source Maps；
      配置webpack插件。
      ```

  - ​      

### 3、[简介springboot如何与vue前后端整合](https://blog.csdn.net/weixin_42603009/article/details/91477222)

​		https://blog.csdn.net/xiaojinlai123/article/details/90694372

### 4、springboot与vue构建哪种安装包

​	将vue的代码构建然后复制到spring的static目录下)

  1. [简单打包合并](https://blog.csdn.net/zhouym_/article/details/100565122)-直接复制

  2. [复杂打包合并](https://www.cnblogs.com/kevinZhu/p/9931317.html)-使用maven执行vue构建，并且复制到springboot的目录下

### 5、[配置虚拟目录](https://blog.csdn.net/zhouym_/article/details/100565122)

### 6、Element UI使用

- 安装依赖

  ```bash
  //查看配置
  npm config list	
  //检查镜像站是否可行命令
  npm config get registry
  //测试获取模块信息
  npm info element-ui
  //安装element-ui
  npm i element-ui -S
  ```

- 引入Element

  引入分为完整引入和按需引入两种模式，按需引入可以缩小项目的体积，这里我们选择完整引入。

  根据文档，我们需要修改 main.js 为如下内容

  ```js
  import Vue from 'vue'
  import App from './App'
  import router from './router'
  import ElementUI from 'element-ui'
  import 'element-ui/lib/theme-chalk/index.css'
  
  var axios = require('axios')
  axios.defaults.baseURL = 'http://localhost:8443/api'
  Vue.prototype.$axios = axios
  Vue.config.productionTip = false
  
  Vue.use(ElementUI)
  
  /* eslint-disable no-new */
  new Vue({
    el: '#app',
    render: h => h(App),
    router,
    components: { App },
    template: '<App/>'
  })
  ```

- 测试案例

  /components/Login.vue

  ```vue
  <template>
    <body id="poster">
      <el-form label-position="left" label-width="0" class="login-container">
        <h3>系统登录</h3>
        <el-form-item>
          <el-input type="text" v-model="loginForm.username" placeholder="账号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="password" v-model="loginForm.password" placeholder="密码" ></el-input>
        </el-form-item>
        <el-form-item style="width:100%;">
          <el-button type="primary" style="width:100%;background:#505458;border: none" v-on:click="login" >登录
          </el-button>
        </el-form-item>
      </el-form>
    </body>
  </template>
  
  <script>
  export default {
    name: 'Login',
    data () {
      return {
        loginForm:{
          username:'admin',
          password:'123'
        },
        responseResult:[]
      }
    },
    methods:{
      login(){
  
        this.$axios.post('/login',{
          username:this.loginForm.username,
          password:this.loginForm.password
        }).then(successResponse => {
          debugger;
          if (successResponse.data.success) {
            this.$router.replace({path: '/index'})
          }
        })
        .catch(failResponse => {
        })
  
      }
    }
  }
  </script>
  
  <style>
    #poster {
      background:url("../assets/eva.jpg") no-repeat;
      background-position: center;
      height: 100%;
      width: 100%;
      background-size: cover;
      position: fixed;
    }
    body{
      margin: 0px;
    }
    .login-container {
      border-radius: 15px;
      background-clip: padding-box;
      margin: 90px auto;
      width: 350px;
      padding: 35px 35px 15px 35px;
      background: #fff;
      border: 1px solid #eaeaea;
      box-shadow: 0 0 25px #cac6c6;
    }
    .login_title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #505458;
    }
  
  </style>
  ```

  //router

  ```js
  import Vue from 'vue'
  import Router from 'vue-router'
  import HelloWorld1 from "@/components/HelloWorld1"
  import Login from "@/components/Login"
  Vue.use(Router)
  
  export default new Router({
    routes: [
      {
        path: '/',
        name: 'Login',
        name: 'Login',
        component: Login
      }
    ]
  })
  
  ```

- 遇到的问题

  ![image-20200217100652535](https://raw.githubusercontent.com/stephenZkang/learn/master/img/node/image-20200217100652535.png)

  解决方法

  ![image-20200217102241136](https://raw.githubusercontent.com/stephenZkang/learn/master/img/node/image-20200217102241136.png)

- 前端路由

  大家如果留心观察就会发现，之前我们做的页面的 URL 里有一个 # 号，这个 # 号有什么含义呢？

  假设在 html 中有这么一段代码：<div id="test">This is a test</div>，如果我们想让页面定位到这个 div 所在的位置，可以加一个超链接 <a herf="#test">Jump to test</a>，这里的 # 被称为“锚点”，点击超链接，可以发现网页的 URL 发生了变化，但页面并不会跳转。

  在互联网流量如此庞大的今天，我们需要想办法后端服务器的压力，利用 AJAX，我们可以不重载页面就刷新数据，如果再加上 # 号的特性（即改变 URL 却不请求后端），我们就可以在前端实现页面的整体变化，而不用每次都去请求后端。

  为了实现前端路由，我们可以监听 # 号后面内容的变化（hashChange），从而动态改变页面内容。URL 的 # 号后面的地址被称为 hash ，估计是哪个大神拍脑袋想的，不用深究。这种实现方式我们称之为 Hash 模式，是非常典型的前端路由方式。

  另一种常用的方式被称为 History 模式，这种方式使用了 History API，History API 顾名思义就是针对历史记录的 API ，这种模式的原理是先把页面的状态保存到一个对象（state）里，当页面的 URL 变化时找到对应的对象，从而还原这个页面。其实原本人家这个功能是为了方便浏览器前进后退的，不得不说程序员们的脑洞真大。使用了这种模式，就可以摆脱 # 号实现前端路由。

  Vue 已经为我们提供了两种模式的前端路由，无需我们自己去实现。

- 使用History模式

  首先我们把 Vue 中配置的路由从默认的 `hash` 模式切换为 `histroy` 模式。打开我们的前端项目 `wj-vue`，修改 `router\index.js`，加入 `mode: 'history` 这句话。整体代码如下：

  ```js
  import Vue from 'vue'
  import Router from 'vue-router'
  import AppIndex from '@/components/home/AppIndex'
  import Login from '@/components/Login'
  
  Vue.use(Router)
  
  export default new Router({
    mode: 'history',
    routes: [
      {
        path: '/login',
        name: 'Login',
        component: Login
      },
      {
        path: '/index',
        name: 'AppIndex',
        component: AppIndex
      }
    ]
  })
  ```

  运行项目，访问不加 # 号的 http://localhost:8080/login ，成功加载页面。

  接下来，我们把前端打包后部署在后端。这不是前后端分离项目推荐的做法，之前我们讲过其实应该把前后端分别部署在不同的服务器中，但实际上仍有不少项目会选择把前后端整合在一起，只使用一个服务器，所以这里我们也提及一下这种方式，但在之后的开发中不会这样部署。

  先在项目目录执行 `npm run build`，控制台输出如下内容表明执行完毕：

  ![image-20200217101656494](https://raw.githubusercontent.com/stephenZkang/learn/master/img/node/image-20200217101656494.png)

  这时在项目的 dist 文件夹下生成了 static 文件夹和 index.html 文件，把这两个文件，拷贝到我们后端项目的 wj\src\main\resources\static 文件夹下，一定要注意这个位置，这时后端配置的静态文件的 path，虽然看起来很诡异，但一般都不作修改。

  

  接下来，打开后端项目并运行，访问 http://localhost:8443/index.html ，（注意输入后缀 .html）发现页面是空白的，但确实取到了这个页面，再访问 http://localhost:8443/login ,发现跳转到了错误页面（White Error Page）。

  

  这里我们回顾一下单页面应用的概念，在我们这个项目中，其实只有 index.html 这一个页面，所有的其它内容都是在这个页面里动态渲染的。当我们直接在后端访问 /login 路径时，服务器会后端并没有这个路径对应的内容，所以返回了 Error Page。

  为了获取到我们需要的内容，我们要想办法触发前端路由，即在后端添加处理内容，把 通过这个 URL 渲染出的 index.html 返回到浏览器。

  在后端项目中新建一个 package 名为 error，新建实现 ErrorPageRegistrar 接口的类 ErrorConfig，把默认的错误页面设置为 /index.html，代码如下

  ```java
  ackage com.evan.wj.error;
  
  import org.springframework.boot.web.server.ErrorPageRegistrar;
  import org.springframework.boot.web.server.ErrorPage;
  import org.springframework.boot.web.server.ErrorPageRegistry;
  import org.springframework.http.HttpStatus;
  import org.springframework.stereotype.Component;
  
  @Component
  public class ErrorConfig implements ErrorPageRegistrar {
  
      @Override
      public void registerErrorPages(ErrorPageRegistry registry) {
          ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
          registry.addErrorPages(error404Page);
      }
  
  }
  ```

  重新启动项目，访问 http://localhost:8443/login ，成功进入登录页面。

- 后端登录拦截器

  为了完善登录功能，我们需要限制未登录状态下对核心功能页面的访问。登录拦截可以由多种方式来实现，我们首先讲解后端拦截器的开发。（注意如果没有把前后端项目整合起来，就没有办法使用这种方式）

  一个简单拦截器的逻辑如下：

  1.用户访问 URL，检测是否为登录页面，如果是登录页面则不拦截
  2.如果用户访问的不是登录页面，检测用户是否已登录，如果未登录则跳转到登录页面

  - LoginController

    首先我们修改 LoginController 的内容。之前我们实现了通过查询数据库验证用户名是否正确，但仅此而已。

    为了保存登录状态，我们可以把用户信息存在 Session 对象中（当用户在应用程序的 Web 页之间跳转时，存储在 Session 对象中的变量不会丢失），这样在访问别的页面时，可以通过判断是否存在用户变量来判断用户是否登录。这是一种比较简单的方式，感兴趣的同学可以尝试别的方法。

    修改后的代码内容如下

    ```java
    package com.hmc.controller;
    
    import java.util.HashMap;
    import java.util.Map;
    
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.CrossOrigin;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.ResponseBody;
    
    @Controller
    public class LoginController {
    
    	@CrossOrigin
    	@RequestMapping(value = "/login",method = RequestMethod.POST)
    	@ResponseBody
    	public Object index(String username,String password) throws Exception {
    		System.out.println("============username="+username);
    		System.out.println("============password="+password);
    		Map<String,Object> result = new HashMap<String, Object>();
    		result.put("success", true);
    		result.put("msg", "成功");
    		return result;
    	}
    	
    }
    ```

  - 问题一、username和password为空

    一开始我是这么写的 ，post里面的数据放在params里面，这样是有问题的，在使用axios时，要注意到配置选项中包含params和data两者，以为他们是相同的，实则不然。 
    因为params是添加到url的请求字符串中的，用于get请求。
    而data是添加到请求体（body）中的， 用于post请求。

    然后我把params改为了data，后台还是接收不到，查阅了很多资料，需要把Content-Type为application/x-www-form-urlencoded，
    jquery在执行post请求时，会设置Content-Type为application/x-www-form-urlencoded，所以服务器能够正确解析，而使用原生ajax、axios请求时，如果不显示的设置Content-Type，那么默认是text/plain，这时服务器就不知道怎么解析数据了，所以才只能通过获取原始数据流的方式来进行解析请求数据。

    打开控制台，报400，报错信息是：传递的参数不存在，但在请求中看的到参数，只是参数的格式是Request Payload，具体也没看懂是什么，总之知道就是参数格式不对，查阅资料找到两种解决办法，代码如下：

    - 方法一：URLSearchParams

      - 在main.js里 设置配置，修改Content-Type

        ```js
        import axios from 'axios';
        axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
        Vue.prototype.$axios = axios;
        ```

      - 在组件vue里

        ```js
        const url ='http://****你的接口****';
        var params = new URLSearchParams();
        params.append('username', this.loginForm.username);
        params.append('password', this.loginForm.password);
        
        this.$axios({
            method: 'post',
            url:url,
            data:params
        }).then((res)=>{
            
        });
        ```

    - 方法二：使用qs

      安装qs,在 main.js里引入

      ```js
      import axios from 'axios';
      import qs from 'qs';
      Vue.prototype.$qs = qs;
      ```

      在vue组件里面请求方法

      ```js
      let postData = this.$qs.stringify({
          key1:value1,
          key2:value2,
          key3:value3,
      });
      
      this.$axios({
          method: 'post',
          url:'url',
          data:postData
      }).then((res)=>{
          
      });
      ```

      

    - 1

- 

## 4、IDEA导入NodeJs项目

- 下载依赖

  ```shell
  npm install
  ```

- 运行项目

  ```shell
  npm run dev
  ```

- 打包程序

  ```shell
  
  ```

  

- 

## 5、vue-element-admin改造

- 更改项目名称

  

- 前端架构图

  ![image-20200305122408309](https://raw.githubusercontent.com/stephenZkang/learn/master/img/node/image-20200305122408309.png)

- Promise的基本用法

  ```js
  // resolve代表成功 reject失败 都是一个函数
  
  let p = new Promise(function(reslove,reject){
  
      //reslove('成功')  //状态由等待变为成功，传的参数作为then函数中成功函数的实参
  
      reject('失败')  //状态由等待变为失败，传的参数作为then函数中失败函数的实参
  
  })
  
  //then中有2个参数，第一个参数是状态变为成功后应该执行的回调函数，第二个参数是状态变为失败后应该执行的回调函数。
  
  p.then((data)=>{
  
      console.log('成功'+data)
  
  },(err)=>{
  
      console.log('失败'+err)
  
  })
  ```

  **vue中Promise的使用**

  ```js
  new Promise((resolve, reject) =>{
      var params = {};
      request(params)
          .then(response => {//成功
              const { data } = response
              commit('SET_TOKEN', data.token)
              setToken(data.token)
              resolve()
          })
          .catch(error => {//失败
              reject(error)
          })
  })
  ```

- **vue:this.$store.dispatch是什么意思？**

  很明显用了单一状态管理库vuex，dispatch派发了一个异步动作，路径惯例为前端接口（与前端代码交互，向后端发送请求，通常为二道贩子，存在于你的js模块下）。

  ![image-20200310174006855](https://raw.githubusercontent.com/stephenZkang/learn/master/img/node/image-20200310174006855.png)


## 6、vue引入xss防止xss攻击

1. 安装xss

   ```shell
   npm install xss --save
   ```

2. vue 页面引入

   ```js
   import xss from ‘xss‘
   ```

3. 测试

   ```html
   <p v-html="test"></p>
    
   export default {
     data () {
       return {
         test: `<a onclick=‘alert("xss攻击")‘>链接</a>`
       }
    // 有弹框
   ```

   修改后

   ```html
   <p v-html="$xss(test)"></p>
    
   import xss from ‘xss‘
   export default {
     data () {
       return {
         test: `<a onclick=‘alert("xss攻击")‘>链接</a>`
       }
   }
    
   Object.defineProperty(Vue.prototype, ‘$xss‘, {
     value: xss
   })
   
   // click事件被过滤
   ```

   

4. 

## 7、vue引入sm4国密加密

1. 安装

   ```shell
   npm install gm-crypt 
   ```

2. vue引入

   ```js
   const SM4 = require("gm-crypt").sm4;
   let sm4Config = {
       //配置sm4参数
       key: "HENG1AN2WEN3YIN4",//这里这个key值是跟后端要的
       mode: "ecb", // 加密的方式有两种，ecb和cbc两种，也是看后端如何定义的，不过要是cbc的话下面还要加一个iv的参数，ecb不用
       cipherType: "base64" // 
   };
   
   let sm4 = new SM4(sm4Config);//这里new一个函数，将上面的sm4Config作为参数传递进去。然后就可以开心的加密了
   let Account = sm4.encrypt(this.Account); //账号加密
   let Pwd = sm4.encrypt(this.Pwd); //密码加密
   ```

## 8、引入外部非模块化js

1. public/index.html

   ```html
   <!DOCTYPE html>
   <html>
     <head>
       <meta charset="utf-8">
       <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
       <meta name="renderer" content="webkit">
       <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
       <link rel="icon" href="<%= BASE_URL %>favicon.ico">
       <title><%= webpackConfig.name %></title>
       <script src="/static/crypto-js.js" type="application/javascript"></script>
       <script src="/static/hex.js" type="application/javascript"></script>
       <script src="/static/byteUtil.js" type="application/javascript"></script>
       <script src="/static/sm4-1.0.js" type="application/javascript"></script>
     </head>
     <body>
       <div id="app"></div>
       <!-- built files will be auto injected -->
     </body>
   </html>
   
   ```

2. 使用

   ```js
   var inputBytes = window.Hex.utf8StrToBytes(content)
   var key = window.Hex.decode('113d6c5d4d7755f692bc5e4a1614ecff')
   var sm4 = new window.SM4()
   var cipher = sm4.encrypt_ecb(key, inputBytes)
   return window.Hex.encode(cipher, 0, cipher.length)
   ```

3. 

## 9、Vue中自定义模块组件传参问题

​	![20171223165335121](D:\workIdea\learn\img\node\20171223165335121.png)

1. 父组件向子组件传参

   子组件中定义prop属性，父组件引用子组件是:属性名=“参数”

   **父组件app向子组件prop-event传递参数address**

   **父组件**

   ```html
   <prop-event-value :address="address" @update="val => address = val" key="4"/>
   <script>
   import propEventValue from './components/prop-event-value.vue'
   export default {
     name: 'app',
     components: {
       propEventValue
     },
     data() {
       return {
         address: ''
       }
     }
   }
   </script>
   ```

   **子组件**

   ```html
   <template>
       <div>
           <p>prop-event</p>
           <label for="address">地址</label>
           <input type="text" id="address" v-model="tempAddress">
       </div>
   </template>
   
   <script>
     export default {
       name: 'prop-event',
       props: [
           address: {
             type: Boolean,
             default: false
           }
       ],
       data() {
         return {
           tempAddress: this.address
         }
       },
       watch: {
         tempAddress(newVal) {
           this.$emit('update', newVal)
         }
       }
     }
   </script>
   ```

   

2. 子组件通过事件向父组件发送消息

   **子组件将获取的值传给父组件的方法saveData**

   **父组件**

   ```html
   <prop-event-value @saveData="saveData" key="4"/>
   <script>
   import propEventValue from './components/prop-event-value.vue'
   export default {
     name: 'app',
     components: {
       propEventValue
     },
     data() {
       return {
         address: ''
       }
     },
     methods() {
         saveData(data){
             
         }
     }
   }
   </script>
   ```

   **子组件**

   ```html
   <template>
       <div>
           <p>prop-event</p>
           <label for="address">地址</label>
           <input type="text" id="address" v-model="tempAddress">
       </div>
   </template>
   
   <script>
     export default {
       name: 'prop-event',
       data() {
         return {
           tempAddress: this.address
         }
       },
       watch: {
         tempAddress(newVal) {
           this.$emit('saveData', newVal)
         }
       }
     }
   </script>
   ```

   