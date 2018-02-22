<template>
  <el-row :gutter="20">
    <el-col :span="10" :offset="7">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>登录</span>
        </div>
        <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
          <el-form-item label="用户名">
            <el-input v-model="formLabelAlign.name"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input type="password" v-model="formLabelAlign.region"></el-input>
          </el-form-item>
          <el-button type="primary" class="right" @click.native="submitForm">登录</el-button>

        </el-form>
      </el-card>

    </el-col>
  </el-row>

</template>

<script>
  export default {
    components: {},
    name: 'Login',
    data() {
      return {
        labelPosition: 'right',
        formLabelAlign: {
          name: '',
          region: '',
          type: ''
        }
      };
    },
    methods: {
      submitForm() {
        this.$ajax({
          url:'/api/login',
          method:'post',
        }).then((res) =>{
          let data = res.data
          if(data.result == 1){
            this.$router.push({
              path: '/hello',
              name: 'HelloWorld',
              params: {
                name: 'name',
                dataObj: this.data
              }
            })
          } else{
            alert(data.msg)
          }
        }).catch(function (err) {
          alert('发生错误，请刷新后重试！');
        })


        /*this.$ajax({
          method: 'post',
          url: '/login',
          data: {
            name: 'wise',
            info: 'wrong'
          }
        })*/
      }
    }
  }
</script>

<style>
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card{
    margin-top: 50px;
    padding-bottom:20px;
  }
</style>
