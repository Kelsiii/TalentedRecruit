<template>
  <div class="main-part">
    <bread-crumb :items="bread_items"></bread-crumb>
    <el-table
      :data="tableData"
      stripe
      style="width: 100%"
    >
      <el-table-column
        prop="date"
        label="更新日期"
        sortable
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="职位名称">
      </el-table-column>
      <el-table-column
        prop="type"
        label="职位类型"
        width="180"
        :filters="[{text: '全职', value: '全职'}, {text: '兼职', value: '兼职'}, {text: '实习', value: '实习'}]"
        :filter-method="filterHandler"
        >
      </el-table-column>
      <el-table-column
        prop="address"
        label="工作地点"
      >
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">查看</el-button>
          <el-button
            v-if="status == 'underway'"
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">结束招聘</el-button>
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
      '$route' () {
        let recruit_type = this.$route.params.type == 'campus' ? '校园招聘' : '社会招聘'
        let recruit_status = this.$route.params.status == 'underway' ? '正在招聘' : '过期职位'

        this.bread_items = [{
          subtitle: '首页',
          path: '/dashboard'
        },{
          subtitle: '职位'
        },{
          subtitle: recruit_type
        },{
          subtitle: recruit_status
        }]
        this.status = this.$route.params.status
      }
    },
    data() {
      let recruit_type = this.$route.params.type == 'campus' ? '校园招聘' : '社会招聘'
      let recruit_status = this.$route.params.status == 'underway' ? '正在招聘' : '过期职位'

      return {
        status: this.$route.params.status,
        bread_items: [
          {
            subtitle: '首页',
            path: '/dashboard'
          },{
            subtitle: '职位'
          },{
            subtitle: recruit_type
          },{
            subtitle: recruit_status
          }
        ],
        tableData: [{
          date: '2016-05-02',
          name: 'UI设计师',
          address: '上海市普陀区金沙江路 1518 弄',
          id: 'unused',
          type: '全职'
        }, {
          date: '2016-05-04',
          name: 'UI设计师',
          address: '上海市普陀区金沙江路 1517 弄',
          type: '全职'
        }, {
          date: '2016-05-01',
          name: 'UI设计师',
          address: '上海市普陀区金沙江路 1519 弄',
          type: '全职'
        }, {
          date: '2016-05-03',
          name: 'UI设计师',
          address: '上海市普陀区金沙江路 1516 弄',
          type: '全职'
        }],
        total: 57,
        count: 10

      }
    },
    methods: {
      handleEdit(index, row) {
        console.log(index, row);
        this.$router.push({ path: `/position/edit/${row.id}` })
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
