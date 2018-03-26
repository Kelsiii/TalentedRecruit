<template>
<form>
  <div class="question-li" v-for="(item, index) in questions">
    <p>{{item.content}}</p>
    <cube-radio-group>
      <cube-radio
        v-for="(option, index) in item.options"
        :option="option.content"
        :value="option.option"
        v-model= "item.selected"
      >
      </cube-radio>
    </cube-radio-group>
  </div>
  <cube-button id="exm-submit-btn">提交</cube-button>
</form>
</template>

<script>
  export default {

    data() {
      return {

        questions: [{
          "id": "3nfjs518",
          "type": "professionalism",
          "content": "在Java中，关于HashMap类的描述，以下错误的是",
          "options": [{
            "option": "A",
            "content": "HashMap使用键/值得形式保存数据"
          }, {
            "option": "B",
            "content": "HashMap 能够保证其中元素的顺序"
          }, {
            "option": "C",
            "content": "HashMap允许将null用作键"
          }, {
            "option": "D",
            "content": "HashMap允许将null用作值"
          }],
          selected:""
        }, {
          "id": "12dasc4g",
          "type": "professionalism",
          "content": "一般情况下，以下哪个选项不是关系数据模型与对象模型之间匹配关系？",
          "options": [{
            "option": "A",
            "content": "表对应类"
          }, {
            "option": "B",
            "content": "记录对应对象"
          }, {
            "option": "C",
            "content": "表的字段对应类的属性"
          }, {
            "option": "D",
            "content": "表之间的参考关系对应类之间的依赖关系"
          }],
          selected:""
        }]
      }
    }
  }
</script>

<style>
  .question-li{
    margin:0 10px 20px 10px;
    font-size: 0.9rem;
  }

  .question-li p{
    padding-bottom: 10px;
    line-height: 20px;
  }

  #exm-submit-btn{
    width: 50%;
    margin:0 auto;
  }
</style>
