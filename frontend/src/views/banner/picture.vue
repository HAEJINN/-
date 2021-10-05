<template>
  <div>
    <div
      class="
        picture-banner
        q-mb-lg q-py-lg
        bg-accent
        column
        justify-center
        items-center
      "
    >
      <div class="text-h3 q-mb-xl">사진 모아보기</div>
      <div class="text-h6 q-mt-md">
        나작화에 올라와 있는 사진 중 몇개를 보여드려요
      </div>
      <div class="text-h6 q-mt-xs">누르면 상세설명을 볼 수 있어요!</div>
    </div>
    <div class="row justify-center">
      <div class="col-lg-8 col-xs-12">
        <div class="row">
          <Picturecomp></Picturecomp>
          <Picturecomp></Picturecomp>
          <Picturecomp></Picturecomp>
          <Picturecomp></Picturecomp>
          <Picturecomp></Picturecomp>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import Picturecomp from "../banner/components/picture-comp.vue";
import "@/styles/banner.scss";

export default defineComponent({
  name: "picture",
  components: {
    Picturecomp,
  },
  setup() {
    return {};
  },
});
</script>
