<template>
  <div>
    <h3 class="position-name">{{form.name}}</h3>
    <ul class="fa-ul none-style-list" id="position-desc">
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
      <!---
      <li>
        <i class="fa fa-li fa-clipboard fa-lg fa-fw"></i>
        <span>{{form.examination.required == 1 ? "需要在线笔试": "不需在线笔试"}}</span>

      </li> --->

    </ul>

    <div class="item-container" id="mobile-container">
      <div class="item-ltitle"><span>职位描述</span></div>
      <p id="description" v-html="form.description"></p>
    </div>


    <cube-button id="submit-btn" @click="submit">一键投递</cube-button>
  </div>

</template>

<script>
  export default {

    data() {
      return {
        form: {
          "id":"",
          "company_id":"",
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
      }
    },

    created(){
      this.loadData()
    },

    methods:{
      submit(){
        if(this.form.examination.required == 1){
          this.$router.push({
            path: `/${this.$route.params.company_id}/mobile/examination/${this.form.id}`
          })
        } else{
          this.$router.push({
            path: `/${this.$route.params.company_id}/mobile/cvinput/${this.form.id}`
          })
        }
      },
      loadData(){
        let position_id = this.$route.params.position_id;
        let company_id = this.$route.params.company_id;
        this.$ajax({
          url: '/api/position/get',
          method: 'post',
          data: {
            company_id: company_id,
            id: position_id
          }
        }).then((res) =>{
          let data = res.data
          if(data.result == 1){
            let position = JSON.parse(data.position[0]);
            this.form.id = position_id;
            this.form.name = position.name;
            this.form.company_id = position.company_id;
            this.form.description = position.description;
            this.form.address = position.address;
            this.form.experience = position.experience;
            this.form.education = position.education;
            this.form.type = position.type;
            this.form.campus = position.campus.toString();
            this.form.salary = position.salary;
            this.form.tags = position.tags.split(";");

            sessionStorage.setItem('companyName', position.name);
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
  .position-name{
    font-weight: 500;
    margin-left: 20px;
    font-size: 1.5rem;
    color: #409EFF;
  }

  #submit-btn{
    width: 80%;
    margin:0 auto;
  }

  #position-desc{
    margin: 30px 20px 50px 20px;
  }

  #description{
    padding: 0 10px ;
    line-height: 1.5rem;
    font-size: 0.875rem;
  }
</style>
