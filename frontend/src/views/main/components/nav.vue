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
      <q-toolbar-title @click="Move(`/`)"> 나만의 작은 화실 </q-toolbar-title>
      <q-btn flat round dense icon="sim_card" class="q-mr-xs" />
      <q-btn flat round dense icon="people" @click="OpenDialog_Login" />
    </q-toolbar>
    <login-dialog
      v-model="state.dialog_login"
      @LoginSuccess="CloseDialog_Login"
    />
    <login-success-dialog v-model="state.dialog_login_success" />
  </div>
</template>

<script>
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";
import LoginDialog from "@/views/user/login-dialog";
import LoginSuccessDialog from "@/views/user/login-success-dialog";

export default {
  components: {
    LoginDialog,
    LoginSuccessDialog,
  },
  setup() {
    const router = useRouter();
    const Move = (path) => {
      router.push(path);
    };
    const state = reactive({
      dialog_login: ref(false),
      dialog_login_success: ref(false),
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

    return {
      state,
      Move,
      OpenDialog_Login,
      CloseDialog_Login,
      OpenDialog_LoginSuccess,
      CloseDialog_LoginSuccess,
    };
  },
};
</script>
