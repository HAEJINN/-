<template>
  <div class="q-pa-sm q-my-md">
    <div class="text-center text-h5 q-mb-md">최근 사진</div>
    <div class="row">
      <PictureComp></PictureComp>
      <PictureComp></PictureComp>
      <PictureComp></PictureComp>
      <PictureComp></PictureComp>
    </div>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import PictureComp from "../components/picture-comp.vue";

export default defineComponent({
  name: "picture-list",
  components: {
    PictureComp,
  },
});
</script>
