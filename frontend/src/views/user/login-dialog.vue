<template lang="">
  <q-dialog>
    <q-layout
      view="lhh LpR lff"
      container
      style="height: 500px"
      class="bg-grey-3"
    >
      <q-page-container>
        <q-header class="bg-black">
          <q-toolbar>
            <q-btn v-close-popup flat round dense icon="close" />
          </q-toolbar>
        </q-header>
        <q-page class="row">
          <a href="/oauth2/authorization/google">
            <img :src="google" />
          </a>
          <a href="/oauth2/authorization/kakao">
            <img :src="kakao" />
          </a>
          <a href="/oauth2/authorization/naver">
            <img :src="naver" />
          </a>
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  setup() {
    return {
      google: require("../../assets/login/google_line.png"),
      kakao: require("../../assets/login/kakao_big.png"),
      naver: require("../../assets/login/naver_green.png"),
    };
  },
});
</script>

<style lang="ts"></style>
