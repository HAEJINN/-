<template>
  <div>
    <div class="q-my-lg q-py-lg bg-accent">
      <div class="text-h4">사진 모아보기</div>
      <div class="q-mt-md">나작화에 올라와 있는 사진 중 몇개를 보여드려요</div>
    </div>
    <div class="row justify-center q-mb-lg">
      <div class="col-lg-8 col-xs-12">
        <div class="row">
          <PictureComp></PictureComp>
          <PictureComp></PictureComp>
          <PictureComp></PictureComp>
          <PictureComp></PictureComp>
          <PictureComp></PictureComp>
          <PictureComp></PictureComp>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import PictureComp from "../banner/components/picture-comp.vue";
export default defineComponent({
  name: "picture",
  components: {
    PictureComp,
  },
});
</script>
