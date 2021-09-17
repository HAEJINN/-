<template>
  <div>
    <div>
      <div>
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
            <label for="name">이름</label>
            <input
              type="text"
              id="name"
              v-model="state.user.name"
              placeholder="이름"
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
          <div>
            <label for="password-confirm">비밀번호 확인</label>
            <input
              type="password"
              id="password-confirm"
              v-model="state.user.passwordConfirm"
              placeholder="비밀번호 확인"
            />
          </div>
          <button type="submit" @click="clickRegister()">회원가입</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import { reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "join",
  setup() {
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
    const clickRegister = () => {
      if (state.user.password === state.user.passwordConfirm) {
        const data = {
          email: state.user.email,
          name: state.user.name,
          password: state.user.password,
        };
        store
          .dispatch("root/request_userSignup", data)
          .then((response) => {
            console.log(response);
            alert("회원가입이 완료되었습니다.");
            router.push("/");
          })
          .catch((error) => {
            console.error(error);
          });
      } else {
        alert("비밀번호가 일치하지 않습니다.");
      }
    };

    return {
      clickRegister,
      state,
    };
  },
});
</script>
