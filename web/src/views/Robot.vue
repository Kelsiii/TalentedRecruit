<template>
<div>
  <div id="dialog-area">

    <message-dialog v-for="(item, index) in messages" v-bind:placement="item.placement">
      <p slot="content" v-if="item.type == 'text'">
        {{item.msg}}
      </p>

      <position-swipe slot="content" v-if="item.type == 'positions'" v-bind:swipeData="item.swipeData">

      </position-swipe>
    </message-dialog>


  </div>

  <div id="input-area">
    <cube-input v-model="user_input" @keyup.enter.native="userInput"></cube-input>
    <i class="fa fa-microphone fa-lg" id="voice-input"></i>
  </div>

</div>

</template>

<script>
  import MessageDialog from '@/components/Message'
  import PositionSwipe from '@/components/PositionSwipe'
  export default {
    name: "Robot",
    components: {
      MessageDialog,
      PositionSwipe
    },
    data() {
      return {
        user_input: '',
        messages: [{
          placement:"left",
          type: "text",
          msg: "hello word"
        }, {
          placement:"right",
          type: "text",
          msg: "hello!!!"
        },{
          placement:"left",
          type: "positions",
          swipeData: [{
            id: "position1",
            company_id: "company1",
            name: "UI设计师",
            "description": "1. 能独立负责app和网页的 UE/UI 以及平面设计；2. 熟悉 Apple 的HIG，安卓的material design；3. 熟悉 Sketch, AI, Photoshop 等设计软件",
            address: "上海-浦东新区",
            experience: "3-5年",
            education: "本科及以上",
            type: "全职",
            "campus": "0",
            "valid": "1",
            "datetime": "2018-01-30 18:51:23",
            "salary": "面议",
            "tags": "UI;设计;Web;交互",
            "examination": {
              "required": 1,
              "distribution": [{
                "type": "personality",
                "num": "5"
              }, {
                "type": "logic",
                "num": "5"
              }, {
                "type": "professionalism",
                "num": "10"
              }]
            }
          }]
        }],

      }
    },
    methods: {
      userInput(){
        this.messages.push({
          placement:"right",
          type: "text",
          msg: this.user_input
        })
        this.user_input = ""

      }
    },
    updated:function(){
      this.$nextTick(function(){
        var div = document.getElementById('dialog-area');
        div.scrollTop = div.scrollHeight;
      })
    }
  }
</script>

<style>
  #input-area {
    position: fixed;
    display: flex;
    bottom: 0;
    width: 90%;
    height: 50px;
  }

  #dialog-area{
    height: 500px;
    overflow: auto;
  }

  #input-area .cube-input{
    width: 90%;
    height: 40px;
  }

  #voice-input {
    position: relative;
    top: 10px;
    left: 10px;
    color: #c8c8c8;
  }


</style>
