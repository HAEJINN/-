<template>
  <div class="row">
    <Picturecomp></Picturecomp>
    <Picturecomp></Picturecomp>
    <Picturecomp></Picturecomp>
    <Picturecomp></Picturecomp>
    <Picturecomp></Picturecomp>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import Picturecomp from "../components/picture-comp.vue";

export default defineComponent({
  name: "remainpic",
  components: {
    Picturecomp,
  },
});
</script>
