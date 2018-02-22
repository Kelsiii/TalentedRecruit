<template>
    <el-breadcrumb style="marginBottom:20px" separator-class="el-icon-arrow-right">

      <el-breadcrumb-item v-for="item in items" :to="{path: item.path}">{{item.subtitle}}</el-breadcrumb-item>
    </el-breadcrumb>

</template>


<script>
  export default {
    name: 'bread-crumb',
    props: ["items"],
    methods: {
    }
  }
</script>

