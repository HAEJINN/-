<template>
  <div>
    <q-toolbar>
      <q-btn dense flat round icon="menu">
        <q-menu>
          <div class="q-pa-sm">
            <q-list>
              <q-item clickable>
                <q-item-section @click="mvinfo">나작화 소개</q-item-section>
              </q-item>
              <q-separator />
              <q-item clickable>
                <q-item-section @click="mvcollecinfo"
                  >컬렉션 소개</q-item-section
                >
              </q-item>
              <q-separator />
              <q-item clickable>
                <q-item-section @click="mvpicture"
                  >사진 모아보기</q-item-section
                >
              </q-item>
              <q-separator />
              <q-item clickable>
                <q-item-section @click="mvcollections"
                  >컬렉션 모아보기</q-item-section
                >
              </q-item>
            </q-list>
          </div>
        </q-menu>
      </q-btn>
      <q-toolbar-title @click="moveMain" class="najakhwa">
        나만의 작은 화실
      </q-toolbar-title>
      <q-btn
        class="q-mx-xs"
        v-if="state.isLogin"
        flat
        round
        dense
        icon="account_circle"
        @click="mvMypage"
      />
      <div v-if="state.isLogin" class="inout" @click="openDialogLogin">
        로그아웃
      </div>
      <div v-else class="inout" @click="openDialogLogin">로그인</div>
    </q-toolbar>
    <login-dialog
      v-model="state.dialog.login.main"
      @loginSuccess="openDialogLoginSuccess"
    />
    <login-success-dialog
      v-model="state.dialog.login.success"
      @registerArtist="openDialogRegisterArtist"
    />
  </div>
</template>

<script>
import "../styles/nav.scss";
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";
import LoginDialog from "@/views/login/dialog";
import LoginSuccessDialog from "@/views/login/success-dialog";

export default {
  components: {
    LoginDialog,
    LoginSuccessDialog,
  },
  setup() {
    /*ㅡㅡㅡㅡㅡRouterㅡㅡㅡㅡㅡ*/
    const router = useRouter();
    const moveMain = () => {
      router.push("/");
    };
    const mvMypage = () => {
      router.push("/mypage");
    };
    const mvinfo = () => {
      router.push("/najakhwa");
    };
    const mvcollecinfo = () => {
      router.push("/collection");
    };
    const mvpicture = () => {
      router.push("/picture");
    };
    const mvcollections = () => {
      router.push("/collections");
    };

    /*ㅡㅡㅡㅡㅡStateㅡㅡㅡㅡㅡ*/
    const state = reactive({
      dialog: {
        login: {
          main: ref(false),
          success: ref(false),
        },
        isLogin: false,
      },
    });

    /*ㅡㅡㅡㅡㅡDialogㅡㅡㅡㅡㅡ*/
    const openDialogLogin = () => {
      if (localStorage.getItem("userInfo") != null) {
        localStorage.removeItem("userInfo");
        alert("로그아웃 되었습니다");
        state.isLogin = false;
        router.go;
        router.push("/");
      } else {
        state.dialog.login.main = true;
      }
    };
    const closeDialogLogin = () => {
      state.dialog.login.main = false;
    };
    const openDialogLoginSuccess = () => {
      closeDialogLogin();
      state.dialog.login.success = true;
    };
    const closeDialogLoginSuccess = () => {
      state.dialog.login.success = false;
    };

    /*ㅡㅡㅡㅡㅡ로그인 로그아웃ㅡㅡㅡㅡㅡ*/
    const userstate = localStorage.getItem("userInfo");
    if (userstate) {
      state.isLogin = true;
    }
    return {
      state,
      moveMain,
      mvMypage,
      mvinfo,
      mvcollecinfo,
      mvpicture,
      mvcollections,

      openDialogLogin,
      closeDialogLogin,
      openDialogLoginSuccess,
      closeDialogLoginSuccess,
    };
  },
};
</script>
