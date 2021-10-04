<template>
  <q-dialog>
    <q-layout view="lhh LpR lff" class="picture-layout bg-secondary">
      <q-page-container>
        <q-page class="column justify-between">
          <q-btn class="self-end" v-close-popup flat round dense icon="close" />
          <q-img class="picture-img" fit="contain" :src="malang"></q-img>
          <div class="picture-title q-ml-sm q-mr-sm">{{ title }}</div>
          <div class="q-ml-sm q-mr-sm">{{ name }}</div>
          <div class="q-ma-sm">{{ description }}</div>

          <div v-if="price !== null" class="q-mt-md q-mb-md">
            {{ price }} 원
          </div>
          <q-btn
            v-if="price !== null"
            class="bg-dark"
            text-color="white"
            label="구매하기"
            @click="mvPurchase"
          ></q-btn>

          <div v-else>이미 판매 완료된 상품입니다</div>
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>
<script>
import "../../styles/picture.scss";
import { defineComponent, reactive, ref } from "vue";

export default defineComponent({
  name: "picture",
  props: {
    title: String,
    name: String,
    description: String,
    price: String,
  },
  setup(props, { emit }) {
    const mvPurchase = () => {
      emit("mvPurchase");
    };
    return {
      malang: require("@/assets/mypage/malang.png"),
      fullscreen: ref(false),

      mvPurchase,
    };
  },
});
</script>
