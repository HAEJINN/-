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
    <register-artist-dialog
      v-model="state.dialog.register.artist"
      @registerCertify="openDialogRegisterCertify"
    />
    <register-certify-dialog
      v-model="state.dialog.register.certify"
      @registerSuccess="openDialogRegisterSuccess"
    />
    <register-success-dialog v-model="state.dialog.register.success" />
  </div>
</template>

<script>
import "../styles/nav.scss";
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";
import LoginDialog from "@/views/login/dialog";
import LoginSuccessDialog from "@/views/login/success-dialog";
import RegisterArtistDialog from "@/views/register/aritst-dialog";
import RegisterCertifyDialog from "@/views/register/certify-dialog";
import RegisterSuccessDialog from "@/views/register/success-dialog";

export default {
  components: {
    LoginDialog,
    LoginSuccessDialog,
    RegisterArtistDialog,
    RegisterCertifyDialog,
    RegisterSuccessDialog,
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
        register: {
          artist: ref(false),
          certify: ref(false),
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
    const openDialogRegisterArtist = () => {
      closeDialogLoginSuccess();
      state.dialog.register.artist = true;
    };
    const closeDialogRegisterArtist = () => {
      state.dialog.register.artist = false;
    };
    const openDialogRegisterCertify = (re) => {
      console.log(re);
      state.dialog.register.certify = true;
    };
    const closeDialogRegisterCertify = (complate) => {
      if (complate) {
        state.dialog.register.artist = false;
      }
      state.dialog.register.certify = false;
    };
    const openDialogRegisterSuccess = (success) => {
      console.log(success);
      if (success === true) {
        state.dialog.register.artist = false;
        state.dialog.register.certify = false;
        state.dialog.register.success = true;
      } else {
        // 파일전송 오류메세지 띄우기
      }
    };
    const closeDialogRegisterSuccess = () => {
      state.dialog.register.success = false;
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
      openDialogRegisterArtist,
      closeDialogRegisterArtist,
      openDialogRegisterCertify,
      closeDialogRegisterCertify,
      openDialogRegisterSuccess,
      closeDialogRegisterSuccess,

      // userstate,
    };
  },
};
</script>
