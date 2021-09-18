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
      <q-btn flat round dense icon="sim_card" class="q-mr-xs" />
      <q-btn flat round dense icon="people" @click="OpenDialog_Login" />
    </q-toolbar>
    <login-dialog
      v-model="state.dialog_login"
      @LoginSuccess="CloseDialog_Login"
    />
    <login-success-dialog
      v-model="state.dialog_login_success"
      @RegisterArtist="OpenDialog_RegisterArtist"
    />
    <register-artist-dialog
      v-model="state.dialog_register_artist"
      @Register="OpenDialog_Register"
    />
    <register-dialog v-model="state.dialog_register" />
  </div>
</template>

<script>
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";
import LoginDialog from "@/views/user/login-dialog";
import LoginSuccessDialog from "@/views/user/login-success-dialog";
import RegisterArtistDialog from "@/views/user/register-aritst-dialog";
import RegisterDialog from "@/views/user/components/register-dialog";

export default {
  components: {
    LoginDialog,
    LoginSuccessDialog,
    RegisterArtistDialog,
    RegisterDialog,
  },
  setup() {
    const router = useRouter();
    const Move_Home = () => {
      router.push("/");
    };
    const state = reactive({
      dialog_login: ref(false),
      dialog_login_success: ref(false),
      dialog_register_artist: ref(false),
      dialog_register: ref(false),
    });
    const OpenDialog_Login = () => {
      state.dialog_login = true;
    };
    const CloseDialog_Login = () => {
      state.dialog_login = false;
      OpenDialog_LoginSuccess();
    };
    const OpenDialog_LoginSuccess = () => {
      state.dialog_login_success = true;
    };
    const CloseDialog_LoginSuccess = () => {
      state.dialog_login_success = false;
    };
    const OpenDialog_RegisterArtist = () => {
      CloseDialog_LoginSuccess();
      state.dialog_register_artist = true;
    };
    const CloseDialog_RegisterArtist = () => {
      state.dialog_register_artist = false;
    };
    const OpenDialog_Register = (re) => {
      console.log(re);
      state.dialog_register = true;
    };
    const CloseDialog_Register = (complate) => {
      if (complate) {
        state.dialog_register_artist = false;
      }
      state.dialog_register = false;
    };

    return {
      state,
      Move_Home,
      OpenDialog_Login,
      CloseDialog_Login,
      OpenDialog_LoginSuccess,
      CloseDialog_LoginSuccess,
      OpenDialog_RegisterArtist,
      CloseDialog_RegisterArtist,
      OpenDialog_Register,
      CloseDialog_Register,
    };
  },
};
</script>
