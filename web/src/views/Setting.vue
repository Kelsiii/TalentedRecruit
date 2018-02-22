<template>
  <div class="main-part">
    <bread-crumb :items="bread_items"></bread-crumb>
    <el-row>
      <el-col :span="12">
        <el-form :model="pwdForm" status-icon :rules="pwdRule" ref="pwdForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="原密码" prop="oldPass">
            <el-input type="password" v-model="pwdForm.oldPass" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="newPass">
            <el-input type="password" v-model="pwdForm.newPass" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input type="password" v-model="pwdForm.checkPass" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('pwdForm')">提交</el-button>
            <el-button @click="resetForm('pwdForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>

  </div>
</template>

<script>
  import BreadCrumb from '@/components/BreadCrumb'
  import ElCol from "element-ui/packages/col/src/col";

  export default {
    components: {
      ElCol,
      BreadCrumb
    },
    data() {
      var validateOldPass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入原密码'));
        } else {
          this.$ajax({
            url:'/api/login',
            method:'post',
          }).then((res) =>{
            let data = res.data
            if(data.result == 1){
              this.$refs.pwdForm.validateField('newPass');
              callback();
            } else{
              callback(new Error(data.msg));
            }
          }).catch(function (err) {
            alert('发生错误，请刷新后重试！');
          })

        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入新密码'));
        } else if (value.length < 8){
          callback(new Error('密码应不小于8位'));
        } else {
          if (this.pwdForm.checkPass !== '') {
            this.$refs.pwdForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.pwdForm.newPass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };

      return {
        bread_items: [
          { subtitle: '首页',
            path: '/dashboard'
          },
          { subtitle: '账号设置',
            path: '/setting'
          },
        ],
        pwdForm: {
          oldPass: '',
          newPass: '',
          checkPass: ''
        },
        pwdRule: {
          oldPass: [
            { validator: validateOldPass, trigger: 'blur' }
          ],
          newPass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ]
        }

      }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>
