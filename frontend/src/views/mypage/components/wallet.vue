<template>
  <div>
    <div class="column items-center">
      <div class="wallet"></div>
      <div class="row items-start justify-center">
        <div class="text-h3">{{ state.wallet.balance }}</div>
        <div class="add-btn" @click="chargeCoin"></div>
      </div>
      <div class="text-h6 q-my-md">계좌주소</div>
      <div class="text-h6 q-my-md">{{ state.wallet.address }}</div>
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
        balance: "",
      },
      jwtToken: "",
    });
    onBeforeMount(() => {
      const userinfo = JSON.parse(localStorage.getItem("userInfo"));
      state.jwtToken = userinfo.jwtToken;
      console.log(state.jwtToken);
      store
        .dispatch("root/request_walletaddress", state.jwtToken)
        .then((response) => {
          console.log(response);
          state.wallet.address = response.data.address;
          state.wallet.balance = response.data.balance;
          localStorage.setItem("address", JSON.stringify(state.wallet.address));
        })
        .catch((error) => {
          console.error(error);
        });
    });

    const chargeCoin = () => {
      var yesno = confirm("충전하시겠습니끼?");
      if (yesno) {
        store
          .dispatch("root/request_sendeth", {
            address: state.wallet.address,
            jwtToken: state.jwtToken,
          })
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
            console.error(error);
          });
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
