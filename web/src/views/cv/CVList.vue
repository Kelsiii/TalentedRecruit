<template>
  <div>
    <el-table
      :data="tableData"
      stripe
      style="width: 100%"
    >
      <el-table-column
        prop="submit_time"
        label="提交时间"
        sortable
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名">
      </el-table-column>
      <el-table-column
        prop="education"
        label="学历"
      >
      </el-table-column>
      <el-table-column
        prop="experience"
        label="工作经验"
      >
      </el-table-column>
      <el-table-column
        prop="tel"
        label="联系电话"
      >
      </el-table-column>

      <el-table-column
        prop="email"
        label="邮箱"
      ></el-table-column>
      <el-table-column
        prop="checked"
        label="已查看"
        :formatter="formatter"
        :filters="[{text: '是', value: '1'}, {text: '否', value: '0'}]"
        :filter-method="filterHandler"
      ></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleCheck(scope.$index, scope.row)">查看</el-button>
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
  export default {
    name: 'cv-list',
    props: ["tableData","total","count"],
    methods: {
      handleCheck(index, row) {

        let position_id = this.$route.params.id;
        let company_id = sessionStorage.getItem("companyId")
        let cv_id = row.id

        this.$ajax({
          url: '/api/cv/check',
          method: 'post',
          data: {
            id: cv_id,
            company_id: company_id,
            position_id: position_id
          }
        }).then((res) =>{
          let data = res.data
          if(data.result == 1){

            this.$router.push({ path: `/cv/${position_id}/${row.id}` })
          } else{
            alert(data.msg)
          }
        }).catch(function (err) {
          console.log(err)
          alert('发生错误，请刷新后重试！');
        })


      },
      formatter(row, column) {
        return row.checked == 0 ? '否':'是';
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

<style>
  .checked{
    font-weight: 500;
  }
</style>
