<template>

  <el-row>
    <el-col :span="4">
          <el-menu id="side-menu"
            class="el-menu-vertical-demo"
            :router="true"
            :default-openeds="activeOpeneds"
          >

            <el-submenu index="position" >
              <template slot="title">
                <i class="el-icon-news"></i>
                <span>职位</span>
              </template>

              <el-menu-item-group>
                <template slot="title">社会招聘</template>
                <el-menu-item index="position/social/underway" :route="{path: '/position/social/underway'}">
                  <i class="el-icon-time"></i>
                  <span slot="title">正在招聘</span>
                </el-menu-item>
                <el-menu-item index="position/social/closed" :route="{path: '/position/social/closed'}">
                  <i class="el-icon-circle-close"></i>
                  <span slot="title">过期职位</span>
                </el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group>
                <template slot="title">校园招聘</template>
                <el-menu-item index="position/campus/underway" :route="{path: '/position/campus/underway'}">
                  <i class="el-icon-time"></i>
                  <span slot="title">正在招聘</span>
                </el-menu-item>
                <el-menu-item index="position/campus/closed" :route="{path: '/position/campus/closed'}">
                  <i class="el-icon-circle-close"></i>
                  <span slot="title">过期职位</span>
                </el-menu-item>
              </el-menu-item-group>


            </el-submenu>
            <el-submenu index="question" >
              <template slot="title">
                <i class="el-icon-document"></i>
                <span>试题</span>
              </template>

              <el-menu-item index="questionlist/personality" :route="{path: '/questionlist/personality'}">
                <i class="fa fa-users fa-lg"></i>
                <span slot="title">性格测试</span>
              </el-menu-item>
              <el-menu-item index="questionlist/logic" :route="{path: '/questionlist/logic'}">
                <i class="fa fa-bar-chart fa-lg"></i>
                <span slot="title">数理逻辑</span>
              </el-menu-item>
              <el-menu-item index="questionlist/professionalism" :route="{path: '/questionlist/professionalism'}">
                <i class="fa fa-check-square-o fa-lg"></i>
                <span slot="title">专业技能</span>
              </el-menu-item>
              <el-menu-item index="question/add" :route="{path: '/question/add/new'}">
                <i class="fa fa-plus-square fa-lg"></i>
                <span slot="title">添加试题</span>
              </el-menu-item>
            </el-submenu>
            <el-menu-item index="info" :route="{path: '/info'}">
              <i class="el-icon-info"></i>
              <span slot="title">企业信息</span>
            </el-menu-item>
            <el-menu-item index="setting" :route="{path: '/setting'}">
              <i class="el-icon-setting"></i>
              <span slot="title">账号设置</span>
            </el-menu-item>
          </el-menu>
    </el-col>


      <el-col :span="20">
          <router-view/>
      </el-col>
  </el-row>


</template>


<script>
  export default {
    data(){
      return {
        "activeOpeneds" : ["question","position"]
      }
    },
    methods: {

    }
  }
</script>

<style>
  #side-menu{
    min-height: 650px;
  }
</style>

