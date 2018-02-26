<template>
  <div class="main-part">
    <bread-crumb :items="bread_items"></bread-crumb>
    <el-row>
      <el-col :span="20">
        <div class="item-container">
          <div class="item-ltitle"><span>基本信息</span></div>
          <el-button class="item-btn" plain icon="el-icon-edit" @click="goEdit"></el-button>
          <ul class="none-style-list">
            <li>
              <i class="fa fa-industry fa-lg"></i>
              <span slot="title">移动互联网,社交网络</span>
            </li>
            <li>
              <i class="fa fa-flag-o fa-lg"></i>
              <span slot="title">北京</span>
            </li>
            <li>
              <i class="fa fa-group fa-lg"></i>
              <span slot="title">150-500人</span>
            </li>
            <li>
              <i class="fa fa-line-chart fa-lg"></i>
              <span slot="title">C轮</span>
            </li>
            <li>
              <i class="fa fa-university fa-lg"></i>
              <span slot="title">北京朝阳区郎家园六号院3号楼4层</span>
            </li>

          </ul>
        </div>
        <div class="item-container">
          <div class="item-ltitle"><span>公司简介</span></div>
          <el-button class="item-btn" plain icon="el-icon-edit" @click="goEdit"></el-button>
          <p id="description">
            果壳网是国内最具影响力的泛科学网络媒体，致力于提供负责任的科学、科技主题内容。<br/>
            自2010年创办以来，果壳网用有趣、多元化的方式在科普、泛知识等领域进行科学传播，吸引了大批乐于接受新知识、新观念，追求品质的科技青年。<br/>
            除新媒体的主品牌之外，果壳网还运营果壳实验室，果壳视频，果壳创客空间等多个子品牌，以及在行、分答、饭团等一系列知识产品。<br/>
            果壳拥有大量各领域专家资源和专业网友，并与国内外科研和学术机构保持密切合作。果壳网倡导科学、理性、积极的生活方式，并希望成为人们身边的科学生活指南。<br/>
            现有全职员工近三百名，先后获得挚信资本、IDG和好未来三轮投资，现正处于C轮的阶段。
          </p>
        </div>

      </el-col>
    </el-row>
  </div>
</template>

<script>
  import BreadCrumb from '@/components/BreadCrumb'

  export default {
    components: {
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
            subtitle: '企业信息',
            path: '/info'
          },
        ]
      }
    },
    methods: {
      goEdit() {
        this.$router.push({
          path: '/edit',
          name: 'InfoEdit'
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
