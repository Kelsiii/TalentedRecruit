<template>
  <div class="swipe-wrapper">
    <cube-scroll>
        <transition-group name="swipe" tag="ul">
          <li class="position-li border-bottom-1px" v-for="(item,index) in swipeData" :key="item.id">

              <div @click="onItemClick(item, index)" class="item-inner">
                <div class="text">
                  <h4 class="item-name" v-html="item.name"></h4>
                  <p class="item-desc">{{`${item.address} | ${item.type}`}}</p>
                </div>
              </div>
          </li>
        </transition-group>
    </cube-scroll>
  </div>
</template>

<script>
  export default {
    name: 'position-swipe',
    props: ["swipeData"],
    methods: {
      onItemClick(item) {
        console.log('click item:', item)
        this.$router.push({ path: `/mobile/position/${item.id}` })
      },
    },
    data() {
      return {

      }
    }
  }
</script>

<style>
  .position-li{
    padding: 5px;
    color: #606266;
  }

  .item-name{
    margin-bottom: 5px;
  }

  .item-desc{
    font-size: 0.5rem;
  }

</style>
