<template>
  <div class="main-part">
    <bread-crumb :items="bread_items"></bread-crumb>
    <el-row>
      <el-col :span="16">
        <el-form ref="form" :model="form" label-width="80px" style="padding:20px 0">
          <el-form-item label="试题类别">
            <el-select v-model="form.type" placeholder="请选择试题类别">
              <el-option label="性格测试" value="personality"></el-option>
              <el-option label="数理逻辑" value="logic"></el-option>
              <el-option label="专业技能" value="professionalism"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="试题内容">
            <el-input type="textarea"  v-model="form.content"></el-input>
          </el-form-item>

          <el-form-item
            v-for="(item, index) in form.options"
            v-bind:label="'选项'+item.option"
            v-bind:id="'option-'+item.option">
            <el-input v-model="item.content"></el-input>
            <el-button
              v-if="index+1 == form.optionNum && index>1"
              @click="deleteOption"
              class="item-delete-btn"
              type="text">
              <i class="el-icon-remove"></i>
            </el-button>
          </el-form-item>

          <el-form-item>
          <el-button type="text" @click="addOption"><i class="el-icon-circle-plus-outline"></i>添加选项</el-button>
          </el-form-item>

          <el-form-item label="正确答案">
            <el-select v-model="form.correct_answer" >

              <el-option v-for="item in form.options" v-bind:label=item.option v-bind:value=item.option></el-option>

            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="cancelForm">取消</el-button>
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
    watch: {
      '$route' () {
        let operation = this.$route.params.operation == 'add' ? '添加试题' : '编辑试题'

        this.bread_items= [
          {
            subtitle: '首页',
            path: '/dashboard'
          },
          {
            subtitle: '试题'
          },
          {
            subtitle: operation
          }
        ]
      }
    },
    data() {
      let operation = this.$route.params.operation == 'add' ? '添加试题' : '编辑试题'
      return {
        bread_items: [
          {
            subtitle: '首页',
            path: '/dashboard'
          },
          {
            subtitle: '试题'
          },
          {
            subtitle: operation
          }
        ],
        form: {
          type:"personality",
          content: "",
          options:[{
            option:'A',
            content:''
          },{
            option:'B',
            content:''
          }],
          optionNum:2,
          correct_answer:"A"

        }

      }
    },
    methods: {
      cancelForm() {
        this.$router.back()
      },

      submitForm() {
        console.log(this.form)

      },

      addOption() {
        let last_option = this.form.options.slice(-1)[0].option
        let next_option = String.fromCharCode(last_option.charCodeAt(0) + 1);
        this.form.options.push({
          option: next_option,
          content: ''
        })
        this.form.optionNum += 1
      },

      deleteOption() {
        this.form.options.pop()
        this.form.optionNum -= 1
      }
    }
  }

</script>

