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
        <q-page class="column justify-center items-center">
          <div class="column justify-center items-center">
            <span class="text-h4 q-my-md">로그인</span>
            <div class="row q-my-sm">
              <label class="q-mr-md" for="email">이메일</label>
              <input
                type="text"
                class="form-control"
                id="email"
                v-model="state.user.email"
                placeholder="이메일"
              />
            </div>
            <div class="row q-my-sm">
              <label class="q-mr-md" for="password">비밀번호</label>
              <input
                type="password"
                id="password"
                v-model="state.user.password"
                placeholder="비밀번호"
              />
            </div>
            <button class="q-my-md" type="submit" @click="clickLogin()">
              로그인
            </button>
          </div>
          <div class="column justify-center items-center q-mt-lg">
            <span class="q-mb-sm">아직 회원이 아니신가요?</span>
            <q-btn
              color="black"
              text-color="white"
              label="회원가입"
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
import { defineComponent } from "vue";
import { reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "login-dialog",
  setup(props, { emit }) {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      user: {
        email: "",
        password: "",
      },
    });
    const openDialogLoginSuccess = () => {
      emit("loginSuccess");
    };
    const clickLogin = () => {
      const data = {
        email: state.user.email,
        password: state.user.password,
      };
      store
        .dispatch("root/request_userLogin", data)
        .then((response) => {
          console.log(response);
          alert("로그인이 완료되었습니다.");
          localStorage.setItem("userInfo", JSON.stringify(response.data));
          router.go();
        })

        .catch((error) => {
          console.error(error);
        });
    };
    return {
      openDialogLoginSuccess,
      clickLogin,
      state,
    };
  },
});
</script>
