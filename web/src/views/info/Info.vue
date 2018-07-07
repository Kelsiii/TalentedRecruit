<template>
  <div class="main-part">
    <bread-crumb :items="bread_items"></bread-crumb>
    <el-row>
      <el-col :span="20">
        <div class="item-container">
          <div class="item-ltitle"><span>基本信息</span></div>
          <el-button class="item-btn" plain icon="el-icon-edit" @click="goEdit"></el-button>
          <ul class="none-style-list fa-ul">
            <li>
              <i class="fa fa-li fa-industry fa-lg"></i>
              <span>{{form.industry}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-map-marker fa-lg"></i>
              <span>{{form.city}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-group fa-lg"></i>
              <span>{{form.scale}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-line-chart fa-lg"></i>
              <span>{{form.process}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-university fa-lg"></i>
              <span>{{form.address}}</span>
            </li>

          </ul>
        </div>
        <div class="item-container">
          <div class="item-ltitle"><span>公司简介</span></div>
          <el-button class="item-btn" icon="el-icon-edit" @click="goEdit"></el-button>

          <p id="description" v-html="form.description"></p>
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
        ],
        form : {
          name: "待填写",
          address: "待填写",
          industry: "待填写",
          city: "待填写",
          scale: "待填写",
          process: "待填写",
          description: "待填写"
        }
      }
    },
    created(){
      this.loadData()
    },
    methods: {
      goEdit() {
        this.$router.push({
          path: '/edit',
          name: 'InfoEdit'
        })
      },
      loadData(){
        let company_id = sessionStorage.getItem("companyId")
        this.$ajax({
          url: '/api/company/get',
          method: 'post',
          data: {
            id: company_id
          }
        }).then((res) =>{
          let data = res.data
          if(data.result == 1){
            let company = JSON.parse(data.company);
            this.form.id = company_id || "待填写"
            this.form.name = company.name || "待填写"
            this.form.address = company.address || "待填写"
            this.form.industry = company.industry || "待填写"
            this.form.city = company.city || "待填写"
            this.form.scale = company.scale || "待填写"
            this.form.process = company.process || "待填写"
            this.form.description = company.description || "待填写"
            this.bread_items.push(
              {subtitle: company.name }
            )

          } else{
            alert(data.msg)
          }

        }).catch(function (err) {
          console.log(err)
          alert('发生错误，请刷新后重试！');
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
