<template>
  <div class="col-lg-4 col-xs-10 q-px-sm q-py-sm">
    <ItemComp></ItemComp>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import ItemComp from "@/views/main/components/item-comp.vue";

export default defineComponent({
  name: "item-list",
  components: {
    ItemComp,
  },
});
</script>
