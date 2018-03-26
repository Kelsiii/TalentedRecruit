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
            subtitle: 'UI设计师',
            path: `/position-detail/position1`
          }, {
            subtitle: '应聘者'
          }
        ],
        form: {
          "id": "3e91bu351",
          "position_id": "position1",
          "company_id": "company1",
          "name": "张某某",
          "gender": "男",
          "tel": "15918274821",
          "email": "abc@qq.com",
          "experience": "3-5年",
          "education": "本科",
          "submit_time": "2018-02-04 08:31:58",
          "checked": "0",
          "examination": {
            "answer_id": "98hebw8mqer2",
            "submit_time": "2018-02-04 08:24:10",
            "accuracy": [
              {
                "type":"total",
                "correct":17,
                "num":20
              },{
                "type":"personality",
                "correct":5,
                "num":5
              },{
                "type":"logic",
                "correct":4,
                "num":5
              },{
                "type":"professionalism",
                "correct":8,
                "num":10
              }
            ],
            "answerSheet": [{
              "questionID": "3bf9web3",
              "answer": "A",
              "correct": 1
            }, {
              "questionID": "8sne8xcw",
              "answer": "D",
              "correct": 0
            }, {
              "questionID": "nd2hwif5",
              "answer": "C",
              "correct": 1
            }]
          }
        }
      }
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
      }
    }
  }

</script>
