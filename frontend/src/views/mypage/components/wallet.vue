<template>
  <div>
    <div class="column items-center">
      <div class="wallet"></div>
      <div class="row items-start justify-center">
        <div class="text-h3">{{ state.wallet.balance }}</div>
        <div class="add-btn" @click="chargeCoin"></div>
      </div>
      <div class="text-h6 q-my-xs">계좌주소</div>
      <div class="text-h6 q-my-md">{{ state.wallet.address }}</div>
    </div>
  </div>
</template>
<script>
import "../../../styles/mypage.scss";
import { defineComponent, onBeforeMount } from "vue";
import { reactive } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

export default defineComponent({
  name: "wallet",
  setup() {
    const store = useStore();
    const router = useRouter();
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
      // console.log(state.jwtToken);
      store
        .dispatch("root/request_walletaddress", state.jwtToken)
        .then((response) => {
          // console.log(response);
          state.wallet.address = response.data.address;
          state.wallet.balance = response.data.balance;
          localStorage.setItem("address", JSON.stringify(state.wallet.address));
        })
        .catch((error) => {
          console.error(error);
        });
    });

    const chargeCoin = () => {
      var yesno = confirm("충전하시겠습니까? 충전금액은 100원입니다");
      if (yesno) {
        const IMP = window.IMP;
        IMP.init("imp35616277");
        IMP.request_pay(
          {
            pg: "html5_inicis",
            pay_method: "card",
            merchant_uid: "merchant_" + new Date().getTime(),
            name: "이더리움 충전",
            amount: 100,
            buyer_email: "xrl0603@naver.com",
            buyer_name: "박지수",
            buyer_tel: "010-4900-7998",
            buyer_addr: "광주광역시 광산구 장덕동",
          },
          (response) => {
            if (response.success) {
              store
                .dispatch("root/request_sendeth", {
                  jwtToken: state.jwtToken,
                  data: {
                    receiver: state.wallet.address,
                    amount: 100,
                    impUid: response.imp_uid,
                    merchantUid: response.merchant_uid,
                  },
                })
                .then((response) => {
                  console.log(response);
                  alert("결제에 성공하였습니다.");
                  router.go();
                })
                .catch((error) => {
                  console.error(error);
                });
            } else {
              alert("결제에 실패하였습니다" + response.error_msg);
            }
          }
        );
      } else {
        alert("충전 취소하였습니다");
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
