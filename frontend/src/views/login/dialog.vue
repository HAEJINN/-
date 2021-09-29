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
        <q-page>
          <div>
            <div>
              <label for="email">이메일</label>
              <input
                type="text"
                class="form-control"
                id="email"
                v-model="state.user.email"
                placeholder="이메일"
              />
            </div>
            <div>
              <label for="password">비밀번호</label>
              <input
                type="password"
                id="password"
                v-model="state.user.password"
                placeholder="비밀번호"
              />
            </div>
            <button type="submit" @click="clickLogin()">로그인</button>
          </div>
          <div class="row justify-center">
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

<script lang="ts">
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
          alert("회원가입이 완료되었습니다.");
          router.push("/");
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
