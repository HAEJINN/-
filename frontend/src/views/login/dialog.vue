<template lang="">
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
            <q-btn v-close-popup flat round dense icon="close" />
          </q-toolbar>
        </q-header>
        <q-page class="column justify-center">
          <div class="row">
            <a
              class="col login-image google"
              href="/oauth2/authorization/google"
            >
            </a>
            <a class="col login-image kakao" href="/oauth2/authorization/kakao">
            </a>
            <a class="col login-image naver" href="/oauth2/authorization/naver">
            </a>
          </div>
          <div class="row justify-center">
            <q-btn
              color="black"
              text-color="white"
              label="로그인 성공^0^"
              @click="openDialogLoginSuccess"
            />
          </div>
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>

<script>
import "../../styles/login.scss";
import { reactive, ref } from "vue";

export default {
  name: "login-dialog",
  setup(props, { emit }) {
    const openDialogLoginSuccess = () => {
      emit("loginSuccess");
    };
    return {
      openDialogLoginSuccess,
    };
  },
};
</script>
