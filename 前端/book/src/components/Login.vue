<template>
  <body id="poster">
    <el-form
      class="login-container"
      label-position="left"
      label-width="0px"
      :model="loginForm"
      ref="loginForm"
    >
      <h3 class="login_title">拾光书屋登录</h3>
      <el-form-item>
        <el-input
          type="text"
          v-model="loginForm.username"
          auto-complete="off"
          placeholder="账号"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          type="password"
          v-model="loginForm.password"
          auto-complete="off"
          placeholder="密码"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button
          type="primary"
          style="width: 100%; background: #505458; border: none"
          @click="submitForm('loginForm')"
          >登录</el-button
        >
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-checkbox
          style="font-size: 15px; color: blue; float: left; margin-left: 10px"
          >记住密码</el-checkbox
        >
        <el-link
          type="primary"
          style="
            background: white;
            font-size: 15px;
            color: blue;
            margin-left: 180px;
          "
          >忘记密码</el-link
        >
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-link
          type="primary"
          style="
            background: white;
            font-size: 12px;
            color: blue;
            margin-left: 180px;
          "
          >没账号？马上注册</el-link
        >
      </el-form-item>
    </el-form>
  </body>
</template>

<script src="web/js/jquery-1.9.1.min.js"></script>
<script src="web/js/jquery.cookie.js"></script>
<script>
import qs from "qs";
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: "wyhui",
        password: "1234",
      }
    };
  },

  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .post("/login?" + qs.stringify(this.loginForm))
            .then((res) => {
              console.log(res);

              const jwt = res.headers["authorization"];
              console.log("jwt",jwt);
              this.$store.commit("SET_TOKEN", jwt);
              this.$router.push("/");
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>
<style>
#poster {
  background: url("../../static/img/eva.jpg") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body {
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
