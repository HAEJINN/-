<template>
  <div>
    <Profile></Profile>
    <Buttons></Buttons>
    <Panner></Panner>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import Profile from "../mypage/components/profile.vue";
import Buttons from "../mypage/components/buttons.vue";
import Panner from "../mypage/components/panner.vue";
export default defineComponent({
  name: "mypage",
  components: {
    Profile,
    Buttons,
    Panner,
  },
});
</script>
