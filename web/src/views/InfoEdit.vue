<template>
  <div class="main-part">
    <bread-crumb :items="bread_items"></bread-crumb>
    <el-row>
      <el-col :span="12">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="所属行业">
            <el-input v-model="form.industry"></el-input>
          </el-form-item>
          <el-form-item label="所在城市">
            <el-input v-model="form.city"></el-input>
          </el-form-item>
          <el-form-item label="企业规模">
            <el-select v-model="form.scale" placeholder="请选择企业规模">
              <el-option label="小于50人" value="小于50人"></el-option>
              <el-option label="50-150人" value="50-150人"></el-option>
              <el-option label="150-500人" value="150-500人"></el-option>
              <el-option label="500-2000人" value="500-2000人"></el-option>
              <el-option label="2000人以上" value="2000人以上"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="融资进展">
            <el-select v-model="form.process" placeholder="请选择融资进展">
              <el-option label="未融资" value="未融资"></el-option>
              <el-option label="天使轮" value="天使轮"></el-option>
              <el-option label="A轮" value="A轮"></el-option>
              <el-option label="B轮" value="B轮"></el-option>
              <el-option label="C轮" value="C轮"></el-option>
              <el-option label="D轮及以上" value="D轮及以上"></el-option>
              <el-option label="上市公司" value="上市公司"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="详细地址">
            <el-input v-model="form.address"></el-input>
          </el-form-item>
          <el-form-item id="description-edit" label="公司简介">
            <el-input type="textarea"  v-model="form.desc"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm()">提交</el-button>
            <el-button @click="cancelForm()">取消</el-button>
          </el-form-item>
        </el-form>
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
          {
            subtitle: '编辑',
            path: '/info/edit'
          }
        ],
        form: {
          name:"果壳网",
          industry:"移动互联网,社交网络",
          city:"北京",
          address:"北京朝阳区郎家园六号院3号楼4层",
          scale:"150-500人",
          process:"C轮",
          desc: '果壳网是国内最具影响力的泛科学网络媒体，致力于提供负责任的科学、科技主题内容。<br/>\n' +
          '            自2010年创办以来，果壳网用有趣、多元化的方式在科普、泛知识等领域进行科学传播，吸引了大批乐于接受新知识、新观念，追求品质的科技青年。<br/>\n' +
          '            除新媒体的主品牌之外，果壳网还运营果壳实验室，果壳视频，果壳创客空间等多个子品牌，以及在行、分答、饭团等一系列知识产品。<br/>\n' +
          '            果壳拥有大量各领域专家资源和专业网友，并与国内外科研和学术机构保持密切合作。果壳网倡导科学、理性、积极的生活方式，并希望成为人们身边的科学生活指南。<br/>\n' +
          '            现有全职员工近三百名，先后获得挚信资本、IDG和好未来三轮投资，现正处于C轮的阶段。'
        }
      }
    },
    methods: {
      cancelForm() {
        this.$router.push({
          path: '/info',
          name: 'Info'
        })
      }
    }
  }

</script>

<style>
  #description-edit textarea{
    min-height: 350px !important;
  }
</style>
