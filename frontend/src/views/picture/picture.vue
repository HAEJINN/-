<template>
  <q-dialog>
    <q-layout
      view="lhh LpR lff"
      container
      style="height: 500px"
      class="bg-secondary"
    >
      <q-page-container>
        <q-header class="bg-dark">
          <q-toolbar>
            <q-toolbar-title>{{ title }}</q-toolbar-title>
            <q-btn v-close-popup flat round dense icon="close" />
          </q-toolbar>
        </q-header>
        <q-page class="column justify-center items-center">
          <div><img :src="malang" /></div>
          <div>{{ name }}</div>
          <div>{{ description }}</div>
          <div v-if="price !== null">
            <div>{{ price }}</div>
            <q-btn label="구매하기" @click="mvPurchase"></q-btn>
          </div>
          <div v-else>
            <div>이미 판매 완료된 상품입니다</div>
          </div>
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>
<script>
import { defineComponent } from "vue";

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

      mvPurchase,
    };
  },
});
</script>
