<template>
  <div class="q-pa-sm q-my-md">
    <div class="text-center text-h5 q-mb-md">최근 사진</div>
    <div class="row">
      <ItemComp></ItemComp>
      <ItemComp></ItemComp>
      <ItemComp></ItemComp>
      <ItemComp></ItemComp>
    </div>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import ItemComp from "../components/item-comp.vue";

export default defineComponent({
  name: "item-list",
  components: {
    ItemComp,
  },
});
</script>
