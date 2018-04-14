
<template>
  <el-container id="app">
    <el-header>
      <el-menu :router="true" mode="horizontal">
        <el-menu-item index="dashboard"><h1>RecruitDashboard</h1></el-menu-item>
        <el-menu-item index="service" class="right"><i class="el-icon-service"></i></el-menu-item>
      </el-menu>
    </el-header>
    <el-main>
      <router-view/>

    </el-main>
  </el-container>
</template>

<script>

  export default {
  components: {},
  name: 'App',
  data() {
    return {
    };
  },
  methods: {
  }
}
</script>


