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
      <q-toolbar-title @click="Move_Home"> 나만의 작은 화실 </q-toolbar-title>
      <q-btn
        flat
        round
        dense
        icon="add_photo_alternate"
        class="q-mr-xs"
        @click="Move_ItemCreate"
      />
      <q-btn
        flat
        round
        dense
        icon="sim_card"
        @click="mvMypage"
        class="q-mr-xs"
      />
      <q-btn flat round dense icon="people" @click="OpenDialog_Login" />
    </q-toolbar>
    <login-dialog
      v-model="state.dialog.login"
      @LoginSuccess="OpenDialog_LoginSuccess"
    />
    <login-success-dialog
      v-model="state.dialog.login_success"
      @RegisterArtist="OpenDialog_RegisterArtist"
    />
    <register-artist-dialog
      v-model="state.dialog.register_artist"
      @RegisterCertify="OpenDialog_RegisterCertify"
    />
    <register-certify-dialog
      v-model="state.dialog.register_certify"
      @RegisterSuccess="OpenDialog_RegisterSuccess"
    />
    <register-success-dialog v-model="state.dialog.register_success" />
  </div>
</template>

<script>
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";
import LoginDialog from "@/views/user/login-dialog";
import LoginSuccessDialog from "@/views/user/login-success-dialog";
import RegisterArtistDialog from "@/views/user/register-aritst-dialog";
import RegisterCertifyDialog from "@/views/user/register-certify-dialog";
import RegisterSuccessDialog from "@/views/user/register-success-dialog";

export default {
  components: {
    LoginDialog,
    LoginSuccessDialog,
    RegisterArtistDialog,
    RegisterCertifyDialog,
    RegisterSuccessDialog,
  },
  setup() {
    const router = useRouter();
    const Move_Home = () => {
      router.push("/");
    };
    const Move_ItemCreate = () => {
      router.push({
        name: "item-create",
      });
    };
    const state = reactive({
      dialog: {
        login: ref(false),
        login_success: ref(false),
        register_artist: ref(false),
        register_certify: ref(false),
        register_success: ref(false),
      },
    });
    const OpenDialog_Login = () => {
      state.dialog.login = true;
    };
    const CloseDialog_Login = () => {
      state.dialog.login = false;
    };
    const OpenDialog_LoginSuccess = () => {
      CloseDialog_Login();
      state.dialog.login_success = true;
    };
    const CloseDialog_LoginSuccess = () => {
      state.dialog.login_success = false;
    };
    const OpenDialog_RegisterArtist = () => {
      CloseDialog_LoginSuccess();
      state.dialog.register_artist = true;
    };
    const CloseDialog_RegisterArtist = () => {
      state.dialog.register_artist = false;
    };
    const OpenDialog_RegisterCertify = (re) => {
      console.log(re);
      state.dialog.register_certify = true;
    };
    const CloseDialog_RegisterCertify = (complate) => {
      if (complate) {
        state.dialog.register_artist = false;
      }
      state.dialog.register_certify = false;
    };
    const OpenDialog_RegisterSuccess = (success) => {
      console.log(success);
      if (success === true) {
        state.dialog.register_artist = false;
        state.dialog.register_certify = false;
        state.dialog.register_success = true;
      } else {
        // 파일전송 오류메세지 띄우기
      }
    };
    const CloseDialog_RegisterSuccess = () => {
      state.dialog.register_success = false;
    };
    const mvMypage = () => {
      router.push("/mypage");
    };

    return {
      state,
      Move_Home,
      Move_ItemCreate,

      OpenDialog_Login,
      CloseDialog_Login,
      OpenDialog_LoginSuccess,
      CloseDialog_LoginSuccess,
      OpenDialog_RegisterArtist,
      CloseDialog_RegisterArtist,
      OpenDialog_RegisterCertify,
      CloseDialog_RegisterCertify,
      OpenDialog_RegisterSuccess,
      CloseDialog_RegisterSuccess,

      mvMypage,
    };
  },
};
</script>
