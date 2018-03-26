<template>
<div>
  <div class="dialog dialog-left" v-if="this.placement == 'left'">
    <i class="fa fa-caret-left fa-lg fa-fw dialog-icon"></i>
    <div class="dialog-frame">
      <slot name="content">
        <div v-html="content"></div>
      </slot>
    </div>

  </div>

  <div class="dialog dialog-right" v-if="this.placement == 'right'">
    <div class="dialog-frame">
      <slot name="content">
        <div v-html="content"></div>
      </slot>
    </div>
    <i class="fa fa-caret-right fa-lg fa-fw dialog-icon"></i>
  </div>

</div>
</template>

<script>
  export default {
    name: 'message-dialog',
    props: ["placement","content"],
    methods: {
    },
    data() {
      return {

      }
    }
  }
</script>

<style>
  .dialog{
    word-wrap:break-word;
  }

  .dialog-icon{
    color: #c8c8c8;
  }

  .dialog-frame {
    border:2px solid;
    border-radius:5px;
    max-width: 60%;
    padding: 10px;

    border-color: #c8c8c8;
  }

  .dialog-left{
    display: flex;
    margin-bottom: 10px;
  }

  .dialog-left .dialog-frame{
    position: relative;
    left: -15px;
    top: 5px;
  }

  .dialog-left .dialog-icon {
    position: relative;
    top:15px;
  }

  .dialog-right{
    display: flex;
    justify-content: flex-end;
    margin-bottom: 10px;
  }

  .dialog-right .dialog-frame{
    position: relative;
    right: -15px;
    top: 10px;
  }
  .dialog-right .dialog-icon {
    position: relative;
    top:20px;
  }


</style>
