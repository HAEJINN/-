<template>
  <div class="bg-accent">
    <div class="row justify-center">
      <div class="col-lg-2 col-xs-8 q-py-sm">
        <q-responsive :ratio="1">
          <div class="profile-image">
            <div class="modify-profile" @click="modifyProfile"></div>
          </div>
        </q-responsive>
      </div>
    </div>
    <div class="text-h6">🤎 {{ state.name }}님 환영합니다 🤎</div>
    <!-- <div class="text-h6 q-my-sm">🤎 디두님 환영합니다 🤎</div> -->
    <div>프로필을 수정해서 자신을 소개해 주세요!</div>
  </div>
</template>
<script>
import "../../../styles/mypage.scss";
import { defineComponent } from "vue";
import { reactive, onBeforeMount } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "profile",
  setup() {
    const router = useRouter();
    const state = reactive({
      // name: "",
    });
    onBeforeMount(() => {
      const userinfo = JSON.parse(localStorage.getItem("userInfo"));
      state.name = userinfo.name;
    });
    const modifyProfile = () => {
      router.push("modify");
    };
    return {
      state,
      onBeforeMount,
      modifyProfile,
    };
  },
});
</script>
