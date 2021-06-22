<template>
  <body>
    <header>
      <div class="header_head">
        <div class="header_head_box">
          <a href="#" class="img"><img style="width: 190px;height: 170px" src="../../static/img/logo.png"></a>
          <ul>
            <li>
              <a @click="toLogin">{{this.welcome_title}}</a>
            </li>
            <span>|</span>
            <li>
              <a @click="toIndex">返回首页</a>
            </li>
          </ul>
        </div>
      </div>
      <div class="header_sous">
        <div class="header_form">
          <input id="searchText" type="text" placeholder="" />
          <el-button icon="el-icon-search" style="border-color:red;background: red;width:30px;height:30px"></el-button>
        </div>
      </div>
      <div class="header_main">
        <div class="header_banner">
          <div class="header_main_left">
            <ul v-for="(item,i) in this.type_lists" :key="i">
              <li>
                <div @click="toCategory((i+1),item.toString())">
                  <a class="header_main_left_a" ctg-data=i><b>{{item.toString()}}</b></a>
                </div>
              </li>
            </ul>
          </div>
          <div class="header_main_center">
            <div class="swiper-container swiper1">
              <div class="swiper-wrapper">
                <div class="swiper-slide">
                  <el-carousel height="340px">
                    <el-carousel-item v-for="(item,i) in this.lunboImages" :key="i">
                      <a href="#"><img class="" :src=item></a>
                    </el-carousel-item>
                  </el-carousel>
                </div>
              </div>
            </div>
          </div>
          <div class="header_main_right">
            <div class="header_main_right_user">
              <div class="user_info">
                <div class="user_info_tou">
                  <a href="#"><img class="" src="../../static/img/touxiang.png"></a>
                </div>
                <div class="user_info_show">
                  <p v-show="welcome_show" style="text-align: left">Hi,{{this.user.username}}, 欢迎来到!</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="header_banner1">
          <a href="#" class="a">
            <img src="" style="height:340px" class="aa" />
          </a>
          <div class="header_banner1_div">
            <p>X</p>
          </div>
        </div>
      </div>
    </header>
   
      <div class="sectionmain">
        <div style="margin-left: 150px;width:1000px;height: 250px;margin-bottom: 20px" class="block">
          <el-carousel height="250px">
            <div class="bookCard" v-for="(item,i) in this.new_lists.slice(0,5)" :key="i">
              <div @click="getBookInfo(item.bookId)">
                <img :src="item.imageUrl" alert="">
                <p>{{item.author}}</p>
                <span>{{item.price}}</span><s>{{item.marketPrice}}</s>
              </div>
            </div>  
            <div class="bookCard" v-for="(item,i) in this.new_lists.slice(5,10)" :key="i">
              <div @click="getBookInfo(item.bookId)">
                <img :src="item.imageUrl" alert="">
                <p>{{item.author}}</p>
                <span>{{item.price}}</span><s>{{item.marketPrice}}</s>
              </div>
            </div> 
            <div class="bookCard" v-for="(item,i) in this.new_lists.slice(10,15)" :key="i">
              <div @click="getBookInfo(item.bookId)">
                <img :src="item.imageUrl" alert="">
                <p>{{item.author}}</p>
                <span>{{item.price}}</span><s>{{item.marketPrice}}</s>
              </div>
            </div> 
            <div class="bookCard" v-for="(item,i) in this.new_lists.slice(15,20)" :key="i">
              <div @click="getBookInfo(item.bookId)">
                <img :src="item.imageUrl" alert="">
                <p>{{item.author}}</p>
                <span>{{item.price}}</span><s>{{item.marketPrice}}</s>
              </div>
            </div> 
                
          </el-carousel>
            
        </div>
      </div>
  </body>
</template>

<script>
import echarts from 'echarts'
import 'echarts-wordcloud/dist/echarts-wordcloud'
import 'echarts-wordcloud/dist/echarts-wordcloud.min'
export default {
  name: 'Index',
  props: {
    className: {
      type: String,
      default: 'chart',
    },
    id: {
      type: String,
      default: 'chart',
    },
    data: {
      type: Array,
      default: [],
    },
    title: {
      type: String,
      default: '热搜词云图',
    },
  },
  data() {
    return {
      new_lists: [], //图书列表
      type_lists: [], //图书种类列表
      welcome_title: '', //标题欢迎
      welcome_show: false, //用户欢迎
      welcome_show1: true, //按钮显示
      token: '', //token
      user: {}, //用户信息
      identity: '', //用户身份
      lunboImages: [
        //轮播图
        'http://img63.ddimg.cn/upload_img/00838/cxtc/750x315_wzh_20200702-1593745049.jpg',
        'http://img60.ddimg.cn/upload_img/00852/ts/pc-1594284328.jpg',
        'http://img60.ddimg.cn/upload_img/00796/ts0708bpms/750x315-1594101386.jpg',
        'http://img61.ddimg.cn/upload_img/00791/xmj/750x315-1593513461.jpg',
      ],
      editState: true, //修改信息
      codeStr: 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789',
      chartdata: [],
      chart: null,
      value: '',
    }
  },
  mounted() {
    this.initChart()
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  created() {
    this.getLoginState()
    this.getData()
    this.getTypeData()
  },
  methods: {
    getRandom(n, m) {
      // param: (Number, Number)
      n = Number(n)
      m = Number(m)
      if (n > m) {
        var temp = n
        n = m
        m = temp
      }
      return Math.floor(Math.random() * (m - n) + n)
    },
    getVertifyCode() {
      var oDiv = document.getElementById('code')
      var str = ''
      for (var i = 0; i < 4; i++) {
        var ran = this.getRandom(0, 62)
        str += this.codeStr.charAt(ran)
      }
      oDiv.innerHTML = str
    },
    toLogin() {
      if (this.welcome_title == '你好，请登录') {
        this.$router.push('/login')
        this.$router.go(0)
      }
    },
    toIndex() {
      this.$router.push('/')
    },
    clearCookies() {
      // 删除所有cookies
      var cookies = document.cookie.split(';')
      for (var i = 0; i < cookies.length; i++) {
        var exp = new Date()
        exp.setTime(exp.getTime() - 1)
        var cookie = cookies[i]
        var eqPos = cookie.indexOf('=')
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie
        var domain = location.host.substr(location.host.indexOf('.'))
        document.cookie = name + '=;expires=' + exp.toGMTString()
      }
    },
    LoginManagement() {
      if (this.value == '登录') {
        this.toLogin()
      } else {
        this.welcome_show1 = !this.welcome_show1
        this.welcome_show = !this.welcome_show
        this.value = '登录'
        this.welcome_title = '你好，请登录'
        this.$axios
          .get('http://114.116.226.207:8222/userservice/user/logout/' + this.token)
          .then((response) => {
            if (response.data.code === 20000) {
              this.$message('账号已退出登录！')
              this.delCookie('token')
              this.delCookie('store_id')
              this.delCookie('identity')
              this.clearCookies()
            }
          })
      }
    },
    delCookie(name) {
      // 删除cookies
      var exp = new Date()
      exp.setTime(exp.getTime() - 1)
      var cval = getCookie(name)
      if (cval != null) document.cookie = name + '=' + cval + ';expires=' + exp.toGMTString()
    },
    getLoginState() {
      this.token = this.getCookie('token')
      if (this.token == '') {
        //未登录
        this.welcome_show1 = true
        this.welcome_show = false
        this.welcome_title = '你好，请登录'
        this.value = '登录'
      } else {
        this.value = '注销登录'
        this.store_id = this.getCookie('store_id')
        this.identity = this.getCookie('identity')
        this.$axios
          .get('http://114.116.226.207:8222/userservice/user/select/' + this.token)
          .then((response) => {
            this.user = response.data.data.userinfo
            this.welcome_show = true //登录
            this.welcome_show1 = false
            this.welcome_title = '你好，' + this.user.username
            if (this.identity == 'business') {
              this.store_show = true
            }
            this.ruleForm.name = this.user.username
            this.ruleForm.email = this.user.email.split('@')[0]
            this.ruleForm.emailad = '@' + this.user.email.split('@')[1]
            this.ruleForm.age = this.user.age
            this.ruleForm.phone = this.user.phone
            this.ruleForm.gender = this.user.gender
            this.getRecommendData()
          })
      }
    },
    checkCookie(name) {
      // 检测cookie是否存在
      var username = getCookie(name)
      if (username != null && username != '') {
        alert('Welcome again ' + username + '!')
      } else {
        username = prompt('Please enter your name:', '')
        if (username != null && username != '') {
          setCookie('username', username, 365)
        }
      }
    },
    getCookie(c_name) {
      // 获取cookie
      if (document.cookie.length > 0) {
        let c_start
        c_start = document.cookie.indexOf(c_name + '=')
        if (c_start != -1) {
          c_start = c_start + c_name.length + 1
          let c_end
          c_end = document.cookie.indexOf(';', c_start)
          if (c_end == -1) {
            c_end = document.cookie.length
          }
          return unescape(document.cookie.substring(c_start, c_end))
        }
      }
      return ''
    },
    getData() {
      this.$axios.get('http://114.116.226.207:8222/bookservice/book/bookindex').then((response) => {
        this.new_lists = response.data.data.new
        this.top_lists = response.data.data.top
        for (var i = 0; i < this.top_lists.length; i++) {
          if (i == 0) this.top_lists[i].show = true
          else this.top_lists[i].show = false
        }
        for (var i = 0; i < this.top_lists.length; i++) {
          var obj = { name: this.top_lists[i].name, value: 1000 * i - 40 }
          this.chartdata.push(obj)
        }
        this.initChart()
      })
      this.toggle(0)
    },
    getTypeData() {
      this.$axios
        .get('http://114.116.226.207:8222/bookservice/book/bookcategory')
        .then((response) => {
          this.type_lists = response.data.data.category
        })
    },
    getBookInfo: function (id) {
      this.$router.push('/bookinfo/' + id)
    },
    search() {
      var keyword = $('#searchText').val()
      window.location.href = 'http://search.gulimall.com/search.html?keyword=' + keyword
    },
    toggle: function (index) {
      // ES6的新语法，称为箭头函数， 它是语法糖。
      // 当箭头函数函数体中只有一条语句时，return语句可省略。
      this.top_lists.forEach((list, i) => (list.show = i == index))
      // 回调函数， 回调函数有三个形参，list当前循环中对象,i表示当前循环中的索引
      // arr 表示循环的数组本身
      this.top_lists.forEach(function (list, i, arr) {
        return (list.show = i == index)
        if (i == index) {
          this.top_lists[i].show = true
        } else {
          this.top_lists[i].show = false
        }
      })
    },
    initChart() {
      this.chart = echarts.init(document.getElementById(this.id))
      const option = {
        title: {
          text: this.title,
          x: 'center',
        },
        backgroundColor: '#fff',
        // tooltip: {
        //   pointFormat: "{series.name}: <b>{point.percentage:.1f}%</b>"
        // },
        series: [
          {
            type: 'wordCloud',
            //用来调整词之间的距离
            gridSize: 10,
            //用来调整字的大小范围
            // Text size range which the value in data will be mapped to.
            // Default to have minimum 12px and maximum 60px size.
            sizeRange: [14, 10],
            // Text rotation range and step in degree. Text will be rotated randomly in range [-90,                                                                             90] by rotationStep 45
            //用来调整词的旋转方向，，[0,0]--代表着没有角度，也就是词为水平方向，需要设置角度参考注释内容
            // rotationRange: [-45, 0, 45, 90],
            // rotationRange: [ 0,90],
            rotationRange: [0, 0],
            //随机生成字体颜色
            // maskImage: maskImage,
            textStyle: {
              normal: {
                color: function () {
                  return (
                    'rgb(' +
                    Math.round(Math.random() * 255) +
                    ', ' +
                    Math.round(Math.random() * 255) +
                    ', ' +
                    Math.round(Math.random() * 255) +
                    ')'
                  )
                },
              },
            },
            //位置相关设置
            // Folllowing left/top/width/height/right/bottom are used for positioning the word cloud
            // Default to be put in the center and has 75% x 80% size.
            left: 'center',
            top: 'center',
            right: null,
            bottom: null,
            width: '200%',
            height: '200%',
            //数据
            data: this.chartdata,
          },
        ],
      }
      this.chart.setOption(option)
    },
  },
}
</script>


<style scoped>
@import '../../static/css/GL.css';
.el-carousel__item h3  {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}
.bookCard {
  width: 18%;
  margin-left: 5px;
  height: 240px;
  display: inline-block;
  background-color: white;
}
</style>
