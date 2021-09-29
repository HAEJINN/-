<template>
  <div>
    <Followitem></Followitem>
    <Followitem></Followitem>
    <Followitem></Followitem>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import Followitem from "../components/followitem.vue";
export default defineComponent({
  name: "follower",
  components: {
    Followitem,
  },
});
</script>
