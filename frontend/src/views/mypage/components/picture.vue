<template>
  <div class="row">
    <picture-comp></picture-comp>
    <picture-comp></picture-comp>
    <picture-comp></picture-comp>
    <picture-comp></picture-comp>
    <picture-comp></picture-comp>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import PictureComp from "../components/picture-comp.vue";

export default defineComponent({
  name: "mypage-picture",
  components: {
    PictureComp,
  },
});
</script>
