<template>
<body>
<header style="height: 140px">

  <div class="header_head">
    <div class="header_head_box">
      <a href="#" class="img"><img  style="float:left;width: 130px;height: 130px" src="../../static/img/logo.png" /></a>
      <b class="header_head_p">
        <a href="#">
        </a>
      </b>
      <ul>
        <span>|</span>
        <li>
          <a @click="toMyOrders">我的订单</a>
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
    <div class="header_ico">
      <div class="header_gw">
        <el-button icon="el-icon-shopping-cart-2"></el-button>
        <span><a>我的购物车</a></span>
      </div>
    </div>
   
  </div>
</header>
<el-divider></el-divider>
<div class="Shop">
  <div class="box">
          <div class="box-one"><!--
            <el-carousel  style="margin-left:-40px;height:500px;width:400px;margin-top: 20px;float: left">
              <el-carousel-item v-for="item in 4" :key="item">-->
                <img :src="this.book_info.imageUrl" style="height:350px;width:350px;" alert="">
             <!-- </el-carousel-item>
            </el-carousel>-->
          </div>
            <div class="box-two">
              <div class="box-name">
                {{this.book_info.name}}
              </div>
              <div class="box-price">
                <ul class="ul">
                  <span >作者</span>
                  <span>{{this.book_info.author}}</span>
                  <span>              </span>
                  <span>{{this.book_info.press}}</span>
                </ul>
              </div>
              <div class="box-price">
                <ul>
                  <span style="font-size: 20px;color: red;font-family: 微软雅黑">惊喜价</span>
                  <span style="font-size: 20px;color: red;font-family: 微软雅黑">￥</span>
                  <span style="font-size: 20px;color: red;font-family: 微软雅黑">{{this.book_info.price}}</span>
                  <s style="font-size: 15px;color: grey;font-family: 微软雅黑" v-show="this.marketprice_show">￥</s>
                  <s style="font-size: 15px;color: grey;font-family: 微软雅黑" v-show="this.marketprice_show">{{this.book_info.marketPrice}}</s>
                </ul>
              </div>
              <div class="box-num">
                <ul>
                  <span>库存</span>
                  <span>{{this.book_info.storeMount}}</span>
                </ul>
              </div>
              <div class="box-dealnum">
                <ul>
                  <span>累计交易数量</span>
                  <span>{{this.book_info.storeMount}}</span>
                </ul>
              </div>
              <div class="box-add">
                  <form id="itemForm" method="post" style="width: 100%;height: 100px;" >
                    <el-input-number v-model="num" class="selectNum"  :min="1"></el-input-number>

                    <el-button  class="box-addTo" type="danger">加入购物车</el-button>
                  </form>
              </div>
            </div>
          </div>
</div>
<div class="tab" style="width: 100%;height: 100px;margin-left: 300px;float: left">
  <el-tabs v-model="activeName" type="card" >
    <el-tab-pane style="width: 100%;margin-bottom: 100px" label="图书详情" name="first">
      <div class="box-name" style="font-size: 20px">
        <ul>
          <span>书名：</span>
          <span>{{this.book_info.name}}</span>
        </ul>
      </div>
      <div class="box-s">
        <ul>
          <span>ISBN编号：</span>
          <span>{{this.book_info.isbn}}</span>
          <span>规格：</span>
          <span>{{this.book_info.packStyle}}</span>
        </ul>
      </div>
      <el-divider></el-divider>
     <!-- <div class="box-s">
        <a href="#"><img class="" style="width: 250px;height: 250px" :src="this.book_info.imageUrl"></a>
      </div>-->
      <div class="box-s">
        <ul>
          <span>作者：</span>
          <span>{{this.book_info.author}}</span>
          <span>出版社：</span>
          <span>{{this.book_info.press}}</span>
          <span>出版日期：</span>
          <span>{{this.book_info.publishDate}}</span>

          <span>上架日期：</span>
          <span>{{this.book_info.storeTime}}</span>
        </ul>
      </div>
      <el-divider></el-divider>
      <br>
      <br>
      <div class="box-s">
        <ul>
          <span>大纲</span>
          <el-divider></el-divider>
          <span>{{this.book_info.outline}}</span>
        </ul>
      </div>
      <el-divider></el-divider>
      <br>
      <br>
      <br>
      <div class="box-s" >
        <div style="margin-right: 50px;">
           <ul>
          <span></span>
          <span>{{this.book_info.detail}}</span>
        </ul>
        </div>
       
      </div>
    </el-tab-pane>
  </el-tabs>
</div>
</body>
</template>

<script>
    export default {
      name: "BookInfo",
      data() {
        return {
          welcome_title:'',
          bookid:0,
          num:1,
          addrShow:false,
          activeName: 'first',
          count:0,
          marketprice_show:true,
          tagsColor: [ 'blue', 'green', 'red', 'yellow' ],
          book_info:{},
          remark_list:[],
          user:{},
          textarea:'',
          item_num:1,
          rate_value:0,
          loginstate:false,
          percent:0,
          flag:null,
          circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
          iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'],
          middle_num:0,
          good_num:0,
          bad_num:0
        }
      },
      created() {
        this.getData();
      },
      methods:{
        toMyOrders(){
          this.$router.push('/myorders/'+this.user.userId)
        },
        toIndex(){
          this.$router.push('/')
        },
        getData(){
          this.bookid=this.$route.params.id;
          this.$axios.get('/bookinfo/'+this.bookid).then(response=>{
            this.book_info=response.data.data;
          })
          console.log(this.book_info);

        },
      }
    }
</script>

<style scoped>
  @import "../../static/css/shop.css";
  @import "../../static/css/GL.css";

  .remarks-container{
    float: left;
  }
  .remarks-title {
    text-align: center;
    padding-left: 15px;
    height: 36px;
    font-size: 16px;
    font-weight: bolder;
    line-height: 36px;
    color: #666666;
    background-color: #F7F7F7;
  }
  .remarks-analyse-box {
    padding: 15px;
    display: flex;
    align-items: center;
  }
  .remarks-analyse-goods {
    margin-left: 15px;
    margin-right: 15px;
  }
  .remarks-analyse-num {
    font-size: 26px;
  }
  .remarks-analyse-title {
    font-size: 12px;
    line-height: 20px;
  }
  .remarks-bar {
    padding-left: 15px;
    height: 36px;
    line-height: 36px;
    color: #666666;
    background-color: #F7F7F7;
  }
  .remarks-bar span {
    margin-right: 15px;
  }
  .remarks-box {
    padding: 15px;
    display: flex;
    flex-direction: row;
    border-bottom: 1px #ccc dotted;
  }
  .remarks-user {
    width: 180px;
  }
  .remarks-user-name {
    padding-left: 15px;
  }
  .remarks-content-box {
    width: calc(100% - 180px);
  }
  .remarks-star {
    background-color: #fff;
  }
  .remarks-content {
    font-size: 14px;
    color: #232323;
    line-height: 28px;
  }
  .remarks-sub {
    margin-top: 15px;
    color: #ccc;
  }
  .remarks-time {
    margin-left: 15px;
  }
  .remarks-page {
    margin: 15px;
    display: flex;
    justify-content:flex-end;
  }
</style>
