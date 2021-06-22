<template>
  <body id="poster">
  <el-form class="login-container" label-position="left"
           label-width="0px" :model="loginForm">
    <h3 class="login_title">拾光书屋登录</h3>
    <el-form-item>
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="login()">登录</el-button>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-checkbox v-model="checked" style="font-size: 15px;color: blue;float: left;margin-left: 10px">记住密码</el-checkbox>
      <el-link type="primary" style="background:white;font-size: 15px;color: blue;margin-left: 180px" >忘记密码</el-link>
    </el-form-item>
    <el-form-item style="width: 100%">
      <a href="https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=2021001172689365&scope=auth_user&redirect_uri=http://114.116.226.207:8222/userservice/alipay_login/getAuthInfo&state=init"><img style="float:left;width: 30px;height: 30px" src="../../static/img/zhifubao.png"   alert=""></a>
      <el-link type="primary" style="background:white;font-size: 12px;color: blue;margin-left: 180px" @click="toRegister">没账号？马上注册</el-link>
    </el-form-item>
  </el-form>
  </body>
</template>

<script src="web/js/jquery-1.9.1.min.js"></script>
<script src="web/js/jquery.cookie.js"></script>
<script>
export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: 'wyhui',
        password: '1234'
      },
      responseResult: [],
      checked:false
    }
  },
  methods: {

    login () {
      this.$axios
        .post('http://114.116.226.207:8222/userservice/user/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(response => {
          if (response.data.code == 20000) {
            this.$message('登录成功！');
             var token = response.data.data.token
              var expiresDate= new Date();
              expiresDate.setTime(expiresDate.getTime() + (30*60*1000));
              this.setCookie("token",token,expiresDate)
              this.setCookie("identity",response.data.data.identity,expiresDate)
              this.setCookie("store_id",response.data.data.store_id,expiresDate)
              this.$router.push("/");
          }
          else{
            this.$message('登录失败！');
          }
        })
        .catch(failResponse => {
        })
    },
    setCookie(c_name, value, expiredays) {// 设置cookie
      var exdate = new Date();
      exdate.setDate(exdate.getDate() + expiredays);
      document.cookie = c_name + "=" + escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
    },
  }
}
</script>
<style>
  #poster {
    background:url("../../static/img/eva.jpg") no-repeat;
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
