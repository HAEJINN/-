<template>
  <div class="coloum">
    <!-- nav bar -->
    <Nav></Nav>
    <div class="col-sm-12 col-md-8">메인 베너입니다</div>
    <div class="col-sm-12 col-md-8">전시회 리스트 입니다</div>
    <div class="col-sm-12 col-md-8">최근 등록 작가입니다</div>
    <div class="col-sm-12 col-md-8">달력입니다</div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Nav from "@/views/main/components/nav.vue";

export default defineComponent({
  name: "Main",
  components: {
    Nav,
  },
});
</script>
