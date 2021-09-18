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
        <q-page class="">
          <a href="/oauth2/authorization/google">
            <img :src="google" />
          </a>
          <a href="/oauth2/authorization/kakao">
            <img :src="kakao" />
          </a>
          <a href="/oauth2/authorization/naver">
            <img :src="naver" />
          </a>
          <q-btn
            color="black"
            text-color="white"
            label="login Lets git it~!"
            @click="OpenDialog_LoginSuccess"
          />
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>

<script>
import { reactive, ref } from "vue";

export default {
  name: "login-dialog",
  setup(props, { emit }) {
    const OpenDialog_LoginSuccess = () => {
      emit("LoginSuccess");
    };
    return {
      google: require("../../assets/login/google_line.png"),
      kakao: require("../../assets/login/kakao_big.png"),
      naver: require("../../assets/login/naver_green.png"),

      OpenDialog_LoginSuccess,
    };
  },
};
</script>

<style lang="ts"></style>
