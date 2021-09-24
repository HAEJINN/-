<template>
  <div>
    <Profile></Profile>
    <Buttons></Buttons>
    <Wallet></Wallet>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import Profile from "../mypage/components/profile.vue";
import Buttons from "../mypage/components/buttons.vue";
import Wallet from "../mypage/components/wallet.vue";
export default defineComponent({
  name: "mypage",
  components: {
    Profile,
    Buttons,
    Wallet,
  },
});
</script>
