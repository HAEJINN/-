<template>
  <div>
    <q-toolbar>
      <q-btn dense flat round icon="menu">
        <q-menu>
          <div class="q-pa-sm">
            <q-list>
              <q-item clickable>
                <q-item-section>Menu1</q-item-section>
              </q-item>
              <q-separator />
              <q-item clickable>
                <q-item-section>Menu2</q-item-section>
              </q-item>
              <q-separator />
              <q-item clickable>
                <q-item-section>Menu3</q-item-section>
              </q-item>
              <q-separator />
              <q-item clickable>
                <q-item-section>Menu4</q-item-section>
              </q-item>
            </q-list>
          </div>
        </q-menu>
      </q-btn>
      <q-toolbar-title @click="moveMain"> 나만의 작은 화실 </q-toolbar-title>
      <q-btn
        flat
        round
        dense
        icon="add_photo_alternate"
        class="q-mr-xs"
        @click="moveItemCreate"
      />
      <q-btn
        flat
        round
        dense
        icon="sim_card"
        @click="mvMypage"
        class="q-mr-xs"
      />
      <q-btn flat round dense icon="people" @click="openDialogLogin" />
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
    const moveItemCreate = () => {
      router.push({
        name: "item-create",
      });
    };
    const mvMypage = () => {
      router.push("/mypage");
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
      },
    });

    /*ㅡㅡㅡㅡㅡDialogㅡㅡㅡㅡㅡ*/
    const openDialogLogin = () => {
      state.dialog.login.main = true;
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

    return {
      state,
      moveMain,
      moveItemCreate,
      mvMypage,

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
    };
  },
};
</script>
