<template>
  <div>
    <h4 id="title">请填写您的个人信息</h4>
    <form id="info-form">
      <p class="form-tag">姓名</p>
      <cube-input v-model="name"></cube-input>

      <p class="form-tag">性别</p>
      <cube-select
        v-model="gender"
        :options="genderOptions">
      </cube-select>

      <p class="form-tag">联系电话</p>
      <cube-input v-model="tel"></cube-input>

      <p class="form-tag">邮箱</p>
      <cube-input v-model="email"></cube-input>

      <p class="form-tag">学历</p>
      <cube-select
        v-model="education"
        :options="educationOptions">
      </cube-select>

      <p class="form-tag">工作经验</p>
      <cube-select
        v-model="experience"
        :options="experienceOptions">
      </cube-select>

    </form>
    <cube-button id="form-submit-btn" @click="submitHandler">提交</cube-button>
  </div>

</template>

<script>
  export default {
    data(){
      return {
        name:"",
        gender:"",
        genderOptions:['男','女'],
        tel:"",
        email:"",
        experience:"",
        experienceOptions:['无','1年以下','3-5年','5-10年','10年以上'],
        education:"",
        educationOptions:['大专及以下','本科','硕士','博士']
      }
    },

    methods: {
      submitHandler(){
        this.$ajax({
          url:'/api/cv/submit',
          method:'post',
          data: {
            company_id: this.$route.params.company_id,
            position_id: this.$route.params.position_id,
            name: this.name,
            gender: this.gender,
            tel: this.tel,
            education: this.education,
            email: this.email,
            experience: this.experience
          }
        }).then((res) =>{
          let data = res.data
          if(data.result == 1){
            this.showAlert("投递成功！","")
            this.$router.push({
              path: `/${this.$route.params.company_id}/mobile/chat`
            })
          } else{
            this.showAlert("Error",data.msg)
          }
        }).catch(function (err) {
          this.showAlert("Error","发生错误，请刷新后重试！")
        })
      },
      showAlert(title,text) {
        this.$createDialog({
          type: 'alert',
          title: title,
          content: text,
          icon: 'cubeic-alert'
        }).show()
      }
    }

  }
</script>

<style>
  .form-tag{
    margin: 20px 0 10px 0;
    font-size: 0.9rem;
    color: #606266;
  }

  #title{
    color: #409EFF;
  }

  #info-form{
    margin: 20px 0;
  }

  #form-submit-btn{
    width: 50%;
    margin:0 auto;
  }
</style>
