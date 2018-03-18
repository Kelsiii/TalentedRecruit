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
          <div class="item-ltitle"><span>职位要求</span></div>
          <el-button class="item-btn" icon="el-icon-edit" @click="goEdit"></el-button>
          <p id="description">
            {{form.description}}
          </p>
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
          },
          {
            subtitle: 'UI设计师'
          }
        ],
        form: {
          "id":"position1",
          "company_id":"company1",
          "name":"UI设计师",
          "description":"1. 能独立负责app和网页的 UE/UI 以及平面设计；2. 熟悉 Apple 的HIG，安卓的material design；3. 熟悉 Sketch, AI, Photoshop 等设计软件",
          "address":"上海-浦东新区",
          "experience":"3-5年工作经验",
          "education":"本科及以上",
          "type":"实习",
          "campus":"0",
          "valid":"1",
          "datetime":"2018-01-30 18:51:23",
          "salary":"面议",
          "tags":["UI","设计","Web","交互"],
          "examination":{
            "required": 1,
            "distribution":[{
              "type":"personality",
              "num":"5"
            },{
              "type":"logic",
              "num":"5"
            },{
              "type":"professionalism",
              "num":"10"
            }]
          }
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
    methods: {
      goEdit() {
        this.$router.push({
          path: `/position/edit/${this.form.id}`
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
