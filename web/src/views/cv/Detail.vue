<template>
  <div class="main-part">
    <bread-crumb :items="bread_items"></bread-crumb>
    <el-row>
      <el-col :span="20">
        <div class="item-container">
          <div class="item-ltitle"><span>基本信息</span></div>
          <ul class="fa-ul none-style-list">
            <li>
              <i class="fa-li fa fa-user-o fa-lg fa-fw"></i>
              <span>{{form.name}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-venus-mars fa-lg fa-fw"></i>
              <span>{{form.gender}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-mortar-board fa-lg fa-fw"></i>
              <span>{{form.education}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-black-tie fa-lg fa-fw"></i>
              <span>{{form.experience}}工作经验</span>
            </li>
            <li>
              <i class="fa fa-li fa-phone fa-lg fa-fw"></i>
              <span>{{form.tel}}</span>
            </li>
            <li>
              <i class="fa fa-li fa-envelope fa-lg fa-fw"></i>
              <span>{{form.email}}</span>
            </li>

          </ul>
        </div>

        <div class="item-container" v-if="form.examination">
          <div class="item-ltitle"><span>在线测评</span></div>
          <el-form ref="form" :model="form" label-width="80px" style="padding:20px 0">
          <el-form-item
            v-for="(item, index) in form.examination.accuracy"
            v-bind:label="labelCN(item.type)"
          >
            <el-rate
              v-model="item.correct/item.num*100/20"
              disabled
              show-score
              text-color="#ff9900"
              style="padding:10px 0"
              score-template="{value}">
            </el-rate>
          </el-form-item>
          </el-form>

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
            subtitle: sessionStorage.getItem("companyName"),
            path: `/position-detail/${this.$route.params.position_id}`
          }
        ],
        form: {
          "id": "",
          "position_id": "",
          "company_id": "",
          "name": "",
          "gender": "",
          "tel": "",
          "email": "",
          "experience": "",
          "education": "",
          "submit_time": "",
          "checked": "",
          "examination": {}
        }
      }
    },
    created(){
      this.loadData();
    },
    methods: {
      labelCN(label){
        let label_CN = ''
        switch(label){
          case 'total':
            label_CN = '总分';
            break
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

      loadData(){
        let position_id = this.$route.params.position_id;
        let cv_id = this.$route.params.cv_id;
        let company_id = sessionStorage.getItem("companyId")

        this.$ajax({
          url: '/api/cv/get',
          method: 'post',
          data: {
            company_id: company_id,
            id: cv_id,
            position_id: position_id
          }
        }).then((res) =>{
          let data = res.data
          if(data.result == 1){
            let cv = JSON.parse(data.CV[0]);
            this.form.id = cv_id;
            this.form.position_id = position_id;
            this.form.company_id = company_id;
            this.form.name = cv.name;
            this.form.gender = cv.gender;
            this.form.tel = cv.tel;
            this.form.experience = cv.experience;
            this.form.education = cv.education;
            this.form.email = cv.email;
            this.form.submit_time = cv.submit_time;
            this.form.checked = cv.checked;
            this.bread_items.push(
              {subtitle: cv.name }
            )
          } else{
            alert(data.msg)
          }

        }).catch(function (err) {
          console.log(err)
          alert('发生错误，请刷新后重试！');
        })
      },

    }
  }

</script>
