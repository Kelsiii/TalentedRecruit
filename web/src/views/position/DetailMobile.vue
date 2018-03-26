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
      <li>
        <i class="fa fa-li fa-clipboard fa-lg fa-fw"></i>
        <span>{{form.examination.required == 1 ? "需要在线笔试": "不需在线笔试"}}</span>
      </li>

    </ul>
    <cube-button id="submit-btn" @click="submit">一键投递</cube-button>
  </div>

</template>

<script>
  export default {

    data() {
      return {
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
        }
      }
    },

    methods:{
      submit(){
        if(this.form.examination.required == 1){
          this.$router.push({
            path: `/mobile/examination/${this.form.id}`
          })
        } else{
          this.$router.push({
            path: `/mobile/cvinput/${this.form.id}`
          })
        }
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
</style>
