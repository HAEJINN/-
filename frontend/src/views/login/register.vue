<template lang="">
  <q-dialog>
    <q-layout
      view="lhh LpR lff"
      container
      style="height: 500px"
      class="register-layout bg-secondary"
    >
      <q-page-container>
        <q-header class="bg-dark">
          <q-toolbar>
            <q-toolbar-title></q-toolbar-title>
            <q-btn v-close-popup flat round dense icon="close" />
          </q-toolbar>
        </q-header>
        <q-page class="column justify-center">
          <div class="row justify-center items-center">
            <span class="text-h4 q-mb-lg">회원가입</span>
            <div class="row justify-center">
              <div class="col-9">
                <div class="row justify-center items-center q-my-sm">
                  <label class="col-3" for="email">이메일</label
                  ><input
                    class="col-6"
                    type="text"
                    id="email"
                    v-model="state.user.email"
                    placeholder="이메일"
                  />
                </div>
              </div>
              <div class="col-9">
                <div class="row justify-center items-center q-my-sm">
                  <label class="col-3" for="name">이름</label
                  ><input
                    class="col-6"
                    type="text"
                    id="name"
                    v-model="state.user.name"
                    placeholder="이름"
                  />
                </div>
              </div>
              <div class="col-9">
                <div class="row justify-center items-center q-my-sm">
                  <label class="col-3" for="password">비밀번호</label
                  ><input
                    class="col-6"
                    type="password"
                    id="password"
                    v-model="state.user.password"
                    placeholder="비밀번호"
                  />
                </div>
              </div>
              <div class="col-9">
                <div class="row justify-center items-center q-my-sm">
                  <label class="col-3" for="password-confirm"
                    >비밀번호 확인</label
                  ><input
                    class="col-6"
                    type="password"
                    id="password-confirm"
                    v-model="state.user.passwordConfirm"
                    placeholder="비밀번호 확인"
                  />
                </div>
              </div>
            </div>
            <q-btn
              class="bg-dark q-mt-lg"
              text-color="white"
              label="회원가입"
              @click="clickRegister()"
            />
          </div>
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>

<script>
import "../../styles/register.scss";
import { defineComponent, reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "register",
  setup(props, { emit }) {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      user: {
        email: "",
        name: "",
        password: "",
        passwordConfirm: "",
      },
    });

    const registerSuccess = () => {
      emit("registerSuccess");
    };

    const clickRegister = () => {
      if (state.user.password === state.user.passwordConfirm) {
        const data = {
          email: state.user.email,
          name: state.user.name,
          password: state.user.password,
        };
        store
          .dispatch("root/requestUserRegister", data)
          .then((response) => {
            console.log(response);
            alert("회원가입이 완료되었습니다.");
            router.go();
          })
          .catch((error) => {
            console.error(error);
          });
      } else {
        alert("비밀번호가 일치하지 않습니다.");
      }
    };

    return {
      state,
      registerSuccess,

      clickRegister,
    };
  },
});
</script>

<style lang=""></style>
