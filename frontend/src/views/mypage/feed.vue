<template>
  <div>
    <div class="row justify-center items-center bg-secondary q-py-lg">
      <div class="col-lg-2 col-xs-4">
        <div class="feed-profile"></div>
      </div>
      <div class="col-lg-4 col-xs-7">
        <div class="row justify-center q-mb-md">
          <div class="col-5">
            <div class="text-h6 text-bold">팔로워</div>
            <div class="text-h6">{{ state.followers }}</div>
          </div>
          <div class="col-5">
            <div class="text-h6 text-bold">팔로잉</div>
            <div class="text-h6">{{ state.followings }}</div>
          </div>
        </div>
        <div class="follow-btn">팔로우</div>
      </div>
    </div>
    <div class="bg-accent q-py-lg q-mb-sm text-h4">
      📷 {{ state.user.name }}님의 화실 📷
    </div>
    <div class="row justify-center">
      <div class="row justify-center col-lg-8 col-xs-11">
        <Picture :user_id="user_id"></Picture>
      </div>
    </div>
  </div>
</template>
<script>
import "../../styles/mypage.scss";
import { defineComponent, onBeforeMount, reactive } from "vue";
import Picture from "../mypage/components/picture.vue";
import store from "../../lib/store";

export default defineComponent({
  name: "feed",
  components: {
    Picture,
  },
  props: {
    user_id: Number,
  },
  setup(props, { emit }) {
    const state = reactive({
      user: {},
      followings: "",
      followers: "",
    });
    onBeforeMount(() => {
      console.log(props.user_id);
      store
        .dispatch("root/request_userinfo_byid", props.user_id)
        .then((response) => {
          console.log(response);
          state.user = response.data;
        })
        .catch((error) => {
          console.log(error);
        });

      store
        .dispatch("root/request_followcount", props.user_id)
        .then((response) => {
          console.log(response);
          state.followers = response.data.followerCount;
          state.followings = response.data.followingCount;
        })
        .catch((error) => {
          console.log(error);
        });
    });
    return {
      state,
      onBeforeMount,
    };
  },
});
</script>
