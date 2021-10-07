<template>
  <div class="user-comp col-lg-3 col-xs-6">
    <div class="q-ma-sm bg-accent">
      <img src="../../../assets/profile.png" />
    </div>
    <div>{{ user.name }}</div>
  </div>
</template>

<script>
import { defineComponent, reactive, ref } from "vue";
import "../../../styles/banner.scss";

export default defineComponent({
  name: "user-comp",
  props: {
    user: {
      type: Object,
    },
  },
});
</script>
