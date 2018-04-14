<template>
  <div class="main-part">
    <bread-crumb :items="bread_items"></bread-crumb>
    <el-table
      :data="tableData"
      stripe
      style="width: 100%"
    >
      <el-table-column
        prop="datetime"
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
            v-if="status == 'underway'"
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
            size="mini"
            @click="handleCheck(scope.$index, scope.row)">查看</el-button>
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

        let campus = this.$route.params.type == 'campus' ? 1 : 0
        let valid = this.$route.params.status == 'underway' ? 1 : 0
        this.loadData(1,campus,valid)

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
        tableData: [],
        total: 0,
        count: 10

      }
    },

    created(){
      let campus = this.$route.params.type == 'campus' ? 1 : 0
      let valid = this.$route.params.status == 'underway' ? 1 : 0

      this.loadData(1,campus,valid)
    },

    methods: {
      handleEdit(index, row) {
        //console.log(index, row);
        this.$router.push({ path: `/position/edit/${row.id}` })
      },
      handleCheck(index, row) {
        //console.log(index, row);
        this.$router.push({ path: `/position-detail/${row.id}` })
      },
      handleDelete(index, row) {
        let form = row
        form.valid = 0
        this.$ajax({
          url:'/api/position/update',
          method:'post',
          data: {
            id: form.id,
            company_id: form.company_id,
            name: form.name,
            description: form.description,
            address: form.address,
            experience: form.experience,
            education: form.education,
            type: form.type,
            campus: form.campus,
            valid: form.valid,
            salary: form.salary,
            tags: form.tags
          }
        }).then((res) =>{
          let data = res.data
          if(data.result == 1){
            this.$router.push({
              path: '/dashboard'
            })
          } else{
            alert(data.msg)
          }
        }).catch(function (err) {
          alert('发生错误，请刷新后重试！');
        })
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
        let campus = this.$route.params.type == 'campus' ? 1 : 0
        let valid = this.$route.params.status == 'underway' ? 1 : 0
        this.loadData(currentPage,campus,valid)
      },

      loadData(page,campus,valid){
        this.$ajax({
          url: '/api/position/list',
          method: 'post',
          data: {
            company_id: sessionStorage.getItem("companyId"),
            page: page,
            count: 10,
            campus: campus,
            valid: valid
          }
        }).then((res) =>{
          let data = res.data
          if(data.result == 1){
            this.total = data.total
            this.count = data.count

            let tableData = [];
            data.positions.forEach((item, i) => {
              let position = JSON.parse(item);
              tableData.push(position)
            });

            this.tableData = tableData;

          } else{
            alert(data.msg)
          }
        }).catch(function (err) {
          alert('发生错误，请刷新后重试！');
        })
      }
    }
  }
</script>
