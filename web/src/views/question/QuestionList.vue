<template>
  <div class="main-part">
    <bread-crumb :items="bread_items"></bread-crumb>
    <el-table
      :data="tableData"
      stripe
      style="width: 100%"
    >
      <el-table-column
        prop="content"
        label="试题内容">
      </el-table-column>
      <el-table-column
        prop="type"
        label="试题类型"
        width="150"
      >
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination">
      <el-pagination
        layout="prev, pager, next"
        :total= total
        :page-size = count
        @current-change = "handlePageChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import BreadCrumb from '@/components/BreadCrumb'

  export default {

    components: {
      BreadCrumb
    },
    watch: {
      '$route'() {
        let route_type = this.$route.params.type
        let question_type = ''
        switch(route_type){
          case 'personality':
            question_type = '性格测试';
            break
          case 'logic':
            question_type = '数理逻辑';
            break
          case 'professionalism':
            question_type = '专业技能';
            break
          default:
            question_type = ''
        }

        this.bread_items = [{
          subtitle: '首页',
          path: '/dashboard'
        }, {
          subtitle: '试题'
        }, {
          subtitle: question_type
        }]
      }
    },
    data() {
      let route_type = this.$route.params.type
      let question_type = ''
      switch(route_type){
        case 'personality':
          question_type = '性格测试';
          break
        case 'logic':
          question_type = '数理逻辑';
          break
        case 'professionalism':
          question_type = '专业技能';
          break
        default:
          question_type = ''
      }

      return {
        bread_items: [
          {
            subtitle: '首页',
            path: '/dashboard'
          }, {
            subtitle: '试题'
          }, {
            subtitle: question_type
          }
        ],
        tableData: [{
          content: '1,　1,　2,　3,　5　, ?',
          type: question_type,
          id: 'unused'
        }],
        total: 57,
        count: 10
      }
    },
    methods: {
      handleEdit(index, row) {
        console.log(index, row);
        this.$router.push({ path: `/question/edit/${row.id}` })
      },
      handleDelete(index, row) {
        console.log(index, row);
      },
      formatter(row, column) {
        return row.address;
      },
      filterHandler(value, row, column) {
        const property = column['property'];
        return row[property] === value;
      },
      handlePageChange(currentPage){
        console.log(currentPage)
      }
    }
  }
</script>
