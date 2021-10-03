<template lang="">
  <q-dialog>
    <q-layout view="lhh LpR lff" container class="login-layout bg-secondary">
      <q-page-container>
        <q-header class="bg-dark">
          <q-toolbar>
            <q-toolbar-title></q-toolbar-title>
            <q-btn v-close-popup flat round dense icon="close" />
          </q-toolbar>
        </q-header>
        <q-page class="column justify-center items-center">
          <div class="column justify-center items-center">
            <span class="text-h4 q-my-md">나작화</span>
            <div class="column">
              <div class="row justify-between items-center q-my-sm">
                <label class="col-3" for="email">이메일</label>
                <input
                  class="col-8"
                  type="text"
                  id="email"
                  v-model="state.user.email"
                  placeholder="이메일"
                />
              </div>
              <div class="row justify-between items-center q-my-sm">
                <label class="col-3" for="password">비밀번호</label>
                <input
                  class="col-8"
                  type="password"
                  id="password"
                  v-model="state.user.password"
                  placeholder="비밀번호"
                />
              </div>
            </div>
            <q-btn
              class="bg-dark q-my-md"
              text-color="white"
              label="로그인"
              @click="clickLogin()"
            >
            </q-btn>
          </div>
          <div class="column justify-center items-center q-mt-lg">
            <span class="q-mb-sm">아직 회원이 아니신가요?</span>
            <q-btn
              class="bg-dark"
              text-color="white"
              label="회원가입"
              @click="openRegisterDialog"
            />
          </div>
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>

<script>
import "../../styles/login.scss";
import { defineComponent, reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "login",
  setup(props, { emit }) {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      user: {
        email: "",
        password: "",
      },
    });

    const loginSuccess = () => {
      emit("loginSuccess");
    };
    const openRegisterDialog = () => {
      emit("openRegisterDialog");
    };

    const clickLogin = () => {
      const data = {
        email: state.user.email,
        password: state.user.password,
      };
      store
        .dispatch("root/requestUserLogin", data)
        .then((response) => {
          console.log(response);
          localStorage.setItem("userInfo", JSON.stringify(response.data));

          alert("로그인이 완료되었습니다.");
          loginSuccess();
          router.go();
        })

        .catch((error) => {
          console.error(error);
        });
    };

    return {
      state,
      loginSuccess,
      openRegisterDialog,

      clickLogin,
    };
  },
});
</script>
