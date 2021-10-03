<template>
  <div class="bg-accent">
    <div class="row justify-center">
      <div class="col-lg-2 col-xs-8 q-py-sm">
        <q-responsive :ratio="1">
          <div class="profile-image">
            <div class="modify-profile"></div>
          </div>
        </q-responsive>
      </div>
    </div>
    <!-- <span class="text-h6">🤎 {{ state.name }}님 환영합니다 🤎</span> -->
    <span class="text-h6">🤎 디두님 환영합니다 🤎</span>
  </div>
</template>
<script>
import "../../../styles/mypage.scss";
import { defineComponent } from "vue";
import { reactive, onBeforeMount } from "vue";

export default defineComponent({
  name: "profile",
  setup() {
    const state = reactive({
      // name: "",
    });
    onBeforeMount(() => {
      const userinfo = JSON.parse(localStorage.getItem("userInfo"));
      // state.name = userinfo.name;
    });
    return {
      state,
      onBeforeMount,
    };
  },
});
</script>
