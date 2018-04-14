<template>
  <div class="main-part">
    <bread-crumb :items="bread_items"></bread-crumb>
    <el-row>
      <el-col :span="20">
        <div class="item-container">
          <div class="item-ltitle"><span>基本信息</span></div>
          <el-button class="item-btn" plain icon="el-icon-edit" @click="goEdit"></el-button>
          <ul class="fa-ul none-style-list">
            <li>
              <i class="fa-li fa fa-vcard-o fa-lg fa-fw"></i>
              <span>{{form.name}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-object-ungroup fa-lg fa-fw"></i>
              <span>{{form.type}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-map-marker fa-lg fa-fw"></i>
              <span>{{form.address}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-mortar-board fa-lg fa-fw"></i>
              <span>{{form.education}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-black-tie fa-lg fa-fw"></i>
              <span>{{form.experience}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-dollar fa-lg fa-fw"></i>
              <span>{{form.salary}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-bank fa-lg fa-fw"></i>
              <span>{{form.campus == 1 ? "校园招聘": "社会招聘"}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-clipboard fa-lg fa-fw"></i>
                <span>{{form.examination.required == 1 ? "需要在线笔试": "不需在线笔试"}}</span>
            </li>

          </ul>
        </div>
        <div class="item-container">
          <div class="item-ltitle"><span>职位描述</span></div>
          <el-button class="item-btn" icon="el-icon-edit" @click="goEdit"></el-button>
          <p id="description" v-html="form.description"></p>
        </div>

        <div class="item-container">
          <div class="item-ltitle"><span>投递信息</span></div>
          <cv-list :tableData="cv_info.tableData" :count="cv_info.page_count" :total="cv_info.total_num"></cv-list>
        </div>


      </el-col>
    </el-row>
  </div>
</template>

<script>
  import BreadCrumb from '@/components/BreadCrumb'
  import CvList from "../cv/CVList.vue";

  export default {
    components: {
      CvList,
      BreadCrumb
    },
    data() {
      return {
        bread_items: [
          {
            subtitle: '首页',
            path: '/dashboard'
          },
          {
            subtitle: '职位'
          }
        ],
        form: {
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
        },
        cv_info:{
          tableData: [{
            "id":"3e91bu351",
            "position_id":"position1",
            "company_id":"company1",
            "name":"张某某",
            "gender":"男",
            "tel":"15918274821",
            "email":"abc@qq.com",
            "experience":"3-5年",
            "education":"本科",
            "submit_time":"2018-02-04 08:31:58",
            "checked":"0",
          }, {
            "id":"3e91bu351",
            "position_id":"position1",
            "company_id":"company1",
            "name":"张某某",
            "gender":"男",
            "tel":"15918274821",
            "email":"abc@qq.com",
            "experience":"3-5年",
            "education":"本科",
            "submit_time":"2018-02-04 08:31:58",
            "checked":"1",
          }, {
            "id":"3e91bu351",
            "position_id":"position1",
            "company_id":"company1",
            "name":"张某某",
            "gender":"男",
            "tel":"15918274821",
            "email":"abc@qq.com",
            "experience":"3-5年",
            "education":"本科",
            "submit_time":"2018-02-04 08:31:58",
            "checked":"1",
          }],
          total_num: 57,
          page_count: 10
        },
      }
    },
    created(){
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
          this.form.id = position_id;
          this.form.name = position.name;
          this.form.description = position.description;
          this.form.address = position.address;
          this.form.experience = position.experience;
          this.form.education = position.education;
          this.form.type = position.type;
          this.form.campus = position.campus.toString();
          this.form.salary = position.salary;
          this.form.tags = position.tags.split(";");
          this.bread_items.push(
            {subtitle: position.name }
          )
        } else{
          alert(data.msg)
        }

      }).catch(function (err) {
        console.log(err)
        alert('发生错误，请刷新后重试！');
      })

    },
    methods: {
      goEdit() {
        this.$router.push({
          path: `/position/edit/${this.$route.params.id}`
        })
      }
    }
  }

</script>

<style>
  #description{
    font-size: 0.875em;
    line-height: 2em;
    padding: 0 40px;
  }
</style>
