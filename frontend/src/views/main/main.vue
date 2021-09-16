<template>
  <div>
    <Banner></Banner>
    <div class="col-sm-12 col-md-8">전시회 리스트 입니다</div>
    <div class="col-sm-12 col-md-8">최근 등록 작품입니다</div>
    <div class="col-sm-12 col-md-8">신규 작가입니다</div>
    <div class="col-sm-12 col-md-8">달력입니다</div>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import Banner from "@/views/main/components/banner.vue";
export default defineComponent({
  name: "Main",
  components: {
    Banner,
  },
});
</script>
