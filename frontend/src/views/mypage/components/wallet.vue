<template>
  <div>
    <div class="column items-center">
      <div class="wallet"></div>
      <div class="row items-start justify-center">
        <div class="text-h3">999ETH</div>
        <div class="add-btn" @click="chargeCoin"></div>
      </div>
      <!-- <div class="text-h6 q-my-md">{{ state.address }}</div> -->
    </div>
  </div>
</template>
<script>
import "../../../styles/mypage.scss";
import { defineComponent, onBeforeMount } from "vue";
import { reactive } from "vue";
import { useStore } from "vuex";

export default defineComponent({
  name: "wallet",
  setup() {
    const store = useStore();
    const state = reactive({
      wallet: {
        address: "",
      },
    });
    onBeforeMount(() => {
      store
        .dispatch("root/request_walletaddress")
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.error(error);
        });
    });

    const chargeCoin = () => {
      var yesno = confirm("충전하시겠습니끼?");
      if (yesno) {
        alert("충전시키자");
      } else {
        alert("충전안한대");
      }
    };

    return {
      state,
      onBeforeMount,
      chargeCoin,
    };
  },
});
</script>
