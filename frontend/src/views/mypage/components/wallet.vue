<template>
  <div>
    <div class="column items-center">
      <div class="wallet"></div>
      <div class="row items-start justify-center">
        <div class="text-h3">999ETH</div>
        <div class="add-btn" @click="chargeCoin"></div>
      </div>
      <div class="text-h6 q-my-md">0X123456789</div>
    </div>
  </div>
</template>
<script lang="ts">
import "../../../styles/mypage.scss";
import { defineComponent } from "vue";
export default defineComponent({
  name: "wallet",
  setup() {
    const chargeCoin = () => {
      var yesno = confirm("충전하시겠습니끼?");
      if (yesno) {
        alert("충전시키자");
      } else {
        alert("충전안한대");
      }
    };

    return {
      chargeCoin,
    };
  },
});
</script>
