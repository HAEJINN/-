<template>
  <div class="row">
    <Followitem></Followitem>
    <Followitem></Followitem>
    <Followitem></Followitem>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import Followitem from "../components/followitem.vue";
export default defineComponent({
  name: "following",
  components: {
    Followitem,
  },
});
</script>
