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
      <q-toolbar-title @click="mvMain" class="najakhwa">
        나만의 작은 화실
      </q-toolbar-title>
      <q-btn
        class="q-mx-xs"
        flat
        round
        dense
        icon="account_circle"
        @click="mvMypage"
      />
      <!--v-if="state.isLogin"-->
      <div v-if="state.isLogin" class="inout" @click="clickLoginLogout">
        로그아웃
      </div>
      <div v-else class="inout" @click="clickLoginLogout">로그인</div>
    </q-toolbar>
    <login-dialog
      v-model="state.dialog.login"
      @loginSuccess="closeLoginDailog"
      @openRegisterDialog="openRegisterDialog"
    />
    <register-dialog
      v-model="state.dialog.register"
      @registerSuccess="closeRegisterDialog"
    />
  </div>
</template>

<script>
import "../styles/nav.scss";
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";
import LoginDialog from "@/views/login/login";
import RegisterDialog from "@/views/login/register";

export default {
  components: {
    LoginDialog,
    RegisterDialog,
  },
  setup() {
    /*ㅡㅡㅡㅡㅡRouterㅡㅡㅡㅡㅡ*/
    const router = useRouter();
    const mvMain = () => {
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
        login: ref(false),
        register: ref(false),
      },
      isLogin: false,
    });

    /*ㅡㅡㅡㅡㅡ로그인 로그아웃ㅡㅡㅡㅡㅡ*/
    const userstate = localStorage.getItem("userInfo");
    if (userstate) {
      state.isLogin = true;
    }
    const clickLoginLogout = () => {
      if (localStorage.getItem("userInfo") != null) {
        localStorage.removeItem("userInfo");
        alert("로그아웃 되었습니다");
        state.isLogin = false;
        router.push("/");
      } else {
        openLoginDailog();
      }
    };

    /*ㅡㅡㅡㅡㅡDialogㅡㅡㅡㅡㅡ*/
    const openLoginDailog = () => {
      state.dialog.login = true;
    };
    const closeLoginDailog = () => {
      state.dialog.login = false;
    };
    const openRegisterDialog = () => {
      closeLoginDailog();
      state.dialog.register = true;
    };
    const closeRegisterDialog = () => {
      state.dailog.register = false;
    };

    return {
      state,
      mvMain,
      mvMypage,
      mvinfo,
      mvcollecinfo,
      mvpicture,
      mvcollections,

      clickLoginLogout,

      openLoginDailog,
      closeLoginDailog,
      openRegisterDialog,
      closeRegisterDialog,
    };
  },
};
</script>
