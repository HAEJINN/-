<template>
  <div class="col-lg-4 col-xs-12">
    <div class="row justify-between items-center bg-secondary q-ma-xs q-pa-xs">
      <div class="row items-center">
        <div class="user-img"></div>
        <span>박디두</span>
      </div>
      <div class="mvcollection bg-primary">컬렉션</div>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import "../../../styles/mypage.scss";
export default defineComponent({
  name: "followitem",
});
</script>
