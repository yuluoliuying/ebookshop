<template>

  <body>
    <header>
      <div class="header_head">
        <div class="header_head_box">
          <a href="#" class="img"><img style="width: 190px; height: 170px" src="../../static/img/logo.png" /></a>
          <ul>
            <li>
              <a>{{ this.welcome_title }}</a>
            </li>
            <span>|</span>
            <li>
              <a @click="toOrders">我的订单</a>
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
          <el-button icon="el-icon-search" style="
              border-color: red;
              background: red;
              width: 30px;
              height: 30px;
            "></el-button>
        </div>

      </div>
      <div class="header_main">
        <div class="header_banner">
          <div class="header_main_center">
            <div class="swiper-container swiper1">
              <div class="swiper-wrapper">
                <div class="swiper-slide">
                  <el-carousel height="340px">
                    <el-carousel-item v-for="(item, i) in this.lunboImages" :key="i">
                      <a href="#"><img class="" :src="item" /></a>
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
                  <a href="#"><img class="" src="../../static/img/touxiang.png" /></a>
                </div>
                <div>
                  <p v-show="welcome_show" style="text-align: left">
                    Hi,{{ this.user.username }}, 欢迎来到!
                  </p>
                </div>
              </div>
              <div class="user_info_hide1" style="margin-top: 20px; height: 500px"></div>
            </div>
          </div>
        </div>
        <div class="header_banner1">
          <a href="#" class="a">
            <img src="" style="height: 340px" class="aa" />
          </a>
          <div class="header_banner1_div">
            <p>X</p>
          </div>
        </div>
      </div>
      <div>
        <el-row>
          <el-col :span="5" @click.native="getBookInfo(item.bookId)" v-for="(item, i) in this.new_lists.slice(this.NumPPS, this.NumPPE)" :key="i">
            <el-card :body-style="{ padding: '0px' }">
              <img :src="item.imageUrl" class="image">
              <div style="padding: 14px;">
                <p>{{ item.author }}</p>
                <span>{{ item.price }}元</span>
              </div>
            </el-card>
          </el-col>
        </el-row>
        <div :id="id" :class="className" style="margin-left: 0px; width: 1000px; height: 400px" />
        <el-pagination background layout="prev, pager, next" @current-change="handleCurrentChange" :total="(this.totalNum*10)">
        </el-pagination>
      </div>

    </header>

  </body>
</template>

<script>
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
  },
  data() {
    return {
      totalNum: 0,
      NumPPS: 0,
      NumPPE: 12,
      PageNum:12,//每页的数目
      new_lists: [], //新书列表
      welcome_title: '', //标题欢迎
      welcome_show: true, //用户欢迎
      user: {
        username:""
        }, //用户信息
      identity: '', //用户身份
      lunboImages: [
        //轮播图
        'http://img63.ddimg.cn/upload_img/00838/cxtc/750x315_wzh_20200702-1593745049.jpg',
        'http://img60.ddimg.cn/upload_img/00852/ts/pc-1594284328.jpg',
        'http://img60.ddimg.cn/upload_img/00796/ts0708bpms/750x315-1594101386.jpg',
        'http://img61.ddimg.cn/upload_img/00791/xmj/750x315-1593513461.jpg',
      ],
      codeStr: 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789',
      chartdata: [],
      chart: null,
      value: '',
    }
  },
  created() {
    this.getUserInfo();
    this.getNewLists();
  },
  methods: {
    getUserInfo(){
      	this.$axios.get("/user/userInfo").then(res => {
					let userInfo = res.data.data
          this.user.username=userInfo.username;
          console.log(this.user);
				})
    },
    handleCurrentChange(val){
      console.log("当前页"+val);
      this.NumPPS+=this.PageNum;
      this.NumPPE+=this.PageNum;
    },
    getNewLists() {
      this.$axios.get('/home').then((res) => {
        this.new_lists = res.data.data
        this.setTotal()
      })
    },
    setTotal() {
      let num = this.new_lists.length % this.PageNum
      let length = this.new_lists.length
      if (num === 0) {
        this.totalNum = length / this.PageNum
      } else {
        this.totalNum = (length - num) / this.PageNum + 1
      }
      console.log(this.new_lists.length)
      console.log(this.totalNum)
    },
    toOrders() {
      this.$router.push('/myorders/' + this.user.userId)
      this.$router.go(0)
    },
    toIndex() {
      this.$router.push('/')
    },
    getBookInfo: function (id) {
      this.$router.push('/bookinfo/' + id)
    },
  },
}
</script>


<style scoped>
@import '../../static/css/GL.css';

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}

.clearfix:after {
  clear: both;
}
</style>
