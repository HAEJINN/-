<template>
  <div class="bg-accent">
    <div class="row justify-center">
      <div class="col-lg-2 col-xs-8 q-py-sm">
        <q-responsive :ratio="1">
          <div class="profile-img">
            <img :src="state.profileImage" class="profile-pic" />
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
import { useStore } from "vuex";

export default defineComponent({
  name: "profile",
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      name: "",
      profileImage: "",
    });
    onBeforeMount(() => {
      const userinfo = JSON.parse(localStorage.getItem("userInfo"));
      store
        .dispatch("root/request_userinfo_byid", userinfo.id)
        .then((response) => {
          console.log(response);
          state.user = response.data;
          state.profileImage =
            "https://najakwha.s3.ap-northeast-2.amazonaws.com/" +
            state.user.profileImage;
        })
        .catch((error) => {
          console.log(error);
        });

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
