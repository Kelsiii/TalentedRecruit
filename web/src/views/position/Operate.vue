<template>
  <div class="main-part">
    <bread-crumb :items="bread_items"></bread-crumb>
    <el-row>
      <el-col :span="16">
        <el-form ref="form" :model="form" label-width="80px" style="padding:20px 0">
          <el-form-item label="职位名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="职位类别">
            <el-select v-model="form.type" placeholder="请选择职位类别">
              <el-option label="全职" value="全职"></el-option>
              <el-option label="兼职" value="兼职"></el-option>
              <el-option label="实习" value="实习"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="职位描述">
            <el-input type="textarea"  v-model="form.description"></el-input>
          </el-form-item>
          <el-form-item label="工作地点">
            <el-input v-model="form.address"></el-input>
          </el-form-item>
          <el-form-item label="学历要求">
            <el-input v-model="form.education"></el-input>
          </el-form-item>
          <el-form-item label="工作经验">
            <el-input v-model="form.experience"></el-input>
          </el-form-item>

          <el-form-item label="薪资待遇">
            <el-input v-model="form.salary"></el-input>
          </el-form-item>
          <el-form-item label="招聘途径">
            <el-select v-model="form.campus" placeholder="请选择招聘途径">
              <el-option label="社会招聘" value="0"></el-option>
              <el-option label="校园招聘" value="1"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="职位标签">
          <el-tag
            :key="tag"
            v-for="tag in form.tags"
            closable
            :disable-transitions="false"
            @close="handleClose(tag)">
            {{tag}}
          </el-tag>
          <el-input
            class="input-new-tag"
            v-if="inputVisible"
            v-model="inputValue"
            ref="saveTagInput"
            size="small"
            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm"
          >
          </el-input>

          <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
          </el-form-item>

          <el-form-item
            v-if="form.examination.required == '0'"
          >
            <el-button type="text" @click="addExamination"><i class="el-icon-circle-plus-outline"></i>设置在线测试</el-button>
          </el-form-item>


          <div
            v-if="form.examination.required == '1'"
          >
            <span class="demonstration">试题分布</span>
            <el-form-item
              v-for="(item, index) in form.examination.distribution"
              v-bind:label="labelCN(item.type)"
              >
              <el-input-number v-model="item.num" :min="0" :max="30" label="labelCN(item.type)"></el-input-number>
            </el-form-item>

            <el-form-item>
              <el-button type="text" @click="removeExamination"><i class="el-icon-remove"></i> 取消在线测试</el-button>
            </el-form-item>

          </div>


          <el-form-item>
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="cancelForm">取消</el-button>
          </el-form-item>

        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<style>
  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
</style>

<script>
  import BreadCrumb from '@/components/BreadCrumb'

  export default {
    components: {
      BreadCrumb
    },
    watch: {
      '$route' () {
        let operation = this.$route.params.operation == 'add' ? '添加职位' : '编辑职位'

        this.bread_items= [
          {
            subtitle: '首页',
            path: '/dashboard'
          },
          {
            subtitle: '职位'
          },
          {
            subtitle: operation
          }
        ]
      }
    },
    data() {
      let operation = this.$route.params.operation == 'add' ? '添加职位' : '编辑职位'
      let form = {
        "name":"",
        "description":"",
        "address":"",
        "experience":"",
        "education":"",
        "type":"",
        "campus":"",
        "valid":1,
        "datetime":"",
        "salary":"",
        "tags":[],
        "examination":{}
      }

      return {
        inputVisible: false,
        inputValue: '',
        bread_items: [
          {
            subtitle: '首页',
            path: '/dashboard'
          },
          {
            subtitle: '职位'
          },
          {
            subtitle: operation
          }
        ],
        form: form

      }
    },
    created(){
      if(this.$route.params.operation == 'edit'){
        let position_id = this.$route.params.id;
        this.$ajax({
          url: '/api/position/get',
          method: 'post',
          data: {
            company_id: sessionStorage.getItem("companyId"),
            id: position_id
          }
        }).then((res) =>{
          let data = res.data
          if(data.result == 1){
            let position = JSON.parse(data.position[0]);
            this.form.name = position.name;
            this.form.description = position.description;
            this.form.address = position.address;
            this.form.experience = position.experience;
            this.form.education = position.education;
            this.form.type = position.type;
            this.form.campus = position.campus.toString();
            this.form.salary = position.salary;
            this.form.tags = position.tags.split(";");

          } else{
            alert(data.msg)
          }

        }).catch(function (err) {
          console.log(err)
          alert('发生错误，请刷新后重试！');
        })
      }
    },
    methods: {
      cancelForm() {
        this.$router.back()
      },

      submitForm() {
        console.log(this.form)
        if (this.$route.params.operation == 'add'){
          this.addPosition()
        } else{
          this.editPosition()
        }

      },

      addExamination() {
        this.form.examination.required = 1;
      },

      removeExamination() {
        this.form.examination.required = 0;
      },

      handleClose(tag) {
        this.form.tags.splice(this.form.tags.indexOf(tag), 1);
      },

      showInput() {
        this.inputVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },

      handleInputConfirm() {
        let inputValue = this.inputValue;
        if (inputValue) {
          this.form.tags.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
      },

      labelCN(label){
        let label_CN = ''
        switch(label){
          case 'personality':
            label_CN = '性格测试';
            break
          case 'logic':
            label_CN = '数理逻辑';
            break
          case 'professionalism':
            label_CN = '专业技能';
            break
          default:
            label_CN = ''
        }
        return label_CN
      },

      addPosition(){
        this.$ajax({
          url:'/api/position/add',
          method:'post',
          data: {
            company_id: sessionStorage.getItem("companyId"),
            name: this.form.name,
            description: this.form.description,
            address: this.form.address,
            experience: this.form.experience,
            education: this.form.education,
            type: this.form.type,
            campus: this.form.campus,
            valid: this.form.valid,
            salary: this.form.salary,
            tags: this.form.tags.join(";")
          }
        }).then((res) =>{
          let data = res.data
          if(data.result == 1){
            this.$router.push({
              path: '/positionlist/social/underway'
            })
          } else{
            alert(data.msg)
          }
        }).catch(function (err) {
          alert('发生错误，请刷新后重试！');
        })
      },

      editPosition(){
        this.$ajax({
          url:'/api/position/update',
          method:'post',
          data: {
            id: this.$route.params.id,
            company_id: sessionStorage.getItem("companyId"),
            name: this.form.name,
            description: this.form.description,
            address: this.form.address,
            experience: this.form.experience,
            education: this.form.education,
            type: this.form.type,
            campus: this.form.campus,
            valid: this.form.valid,
            salary: this.form.salary,
            tags: this.form.tags.join(";")
          }
        }).then((res) =>{
          let data = res.data
          if(data.result == 1){
            this.$router.push({
              path: '/positionlist/social/underway'
            })
          } else{
            alert(data.msg)
          }
        }).catch(function (err) {
          alert('发生错误，请刷新后重试！');
        })
      },
    }

  }

</script>
