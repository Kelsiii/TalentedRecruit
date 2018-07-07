<template>
  <div class="main-part">
    <bread-crumb :items="bread_items"></bread-crumb>
    <el-row>
      <el-col :span="16">
        <el-form ref="form" :model="form" label-width="80px" style="padding:20px 0">
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
            <el-input type="textarea"  v-model="form.description"></el-input>
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
    created(){
      this.loadData()
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
          name:"",
          industry:"",
          city:"",
          address:"",
          scale:"",
          process:"",
          description: ''
        }
      }
    },
    methods: {
      cancelForm() {
        this.$router.push({
          path: '/info',
          name: 'Info'
        })
      },

      submitForm(){
        this.$ajax({
          url:'/api/company/update',
          method:'post',
          data: {
            id: sessionStorage.getItem("companyId"),
            name: this.form.name,
            description: this.form.description,
            address: this.form.address,
            industry: this.form.industry,
            city: this.form.city,
            scale: this.form.scale,
            process: this.form.process
          }
        }).then((res) =>{
          let data = res.data
          if(data.result == 1){
            this.$router.push({
              path: '/info'
            })
          } else{
            alert(data.msg)
          }
        }).catch(function (err) {
          alert('发生错误，请刷新后重试！');
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
            this.form.id = company_id
            this.form.name = company.name
            this.form.address = company.address
            this.form.industry = company.industry
            this.form.city = company.city
            this.form.scale = company.scale
            this.form.process = company.process
            this.form.description = company.description
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

