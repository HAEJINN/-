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
        <q-btn
          v-if="user_id != state.myId && !state.followable"
          class="follow-btn"
          @click="following"
        >
          팔로우
        </q-btn>
        <q-btn
          v-if="user_id != state.myId && state.followable"
          class="follow-btn"
          @click="unfollow"
        >
          언팔로우
        </q-btn>
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
import { defineComponent, onBeforeMount, reactive, ref } from "vue";
import Picture from "../mypage/components/picture.vue";
import store from "../../lib/store";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "feed",
  components: {
    Picture,
  },
  props: {
    user_id: Number,
  },
  setup(props, { emit }) {
    const router = useRouter();
    const state = reactive({
      user: {},
      followings: "",
      followers: "",
      myId: JSON.parse(localStorage.getItem("userInfo")).id,
      followable: ref(false),
    });
    onBeforeMount(() => {
      store
        .dispatch("root/request_userinfo_byid", props.user_id)
        .then((response) => {
          console.log(response);
          state.user = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
      followcount();
      followable();
    });

    const followcount = () => {
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
    };

    const followable = () => {
      const data = {
        id: props.user_id,
        jwtToken: JSON.parse(localStorage.getItem("userInfo")).jwtToken,
      };
      store
        .dispatch("root/request_followable", data)
        .then((response) => {
          console.log(response);
          state.followable = response.data;
          console.log(state.followable);
        })
        .catch((error) => {
          console.error(error);
        });
    };

    const following = () => {
      const data = {
        id: props.user_id,
        jwtToken: JSON.parse(localStorage.getItem("userInfo")).jwtToken,
      };
      store
        .dispatch("root/request_following", data)
        .then((response) => {
          console.log(response);
          alert("팔로우 성공");
          followcount();
          followable();
        })
        .catch((error) => {
          console.error(error);
          alert("팔로우 실패");
        });
    };

    const unfollow = () => {
      const data = {
        id: props.user_id,
        jwtToken: JSON.parse(localStorage.getItem("userInfo")).jwtToken,
      };
      store
        .dispatch("root/request_unfollow", data)
        .then((response) => {
          console.log(response);
          alert("언팔로우 성공");
          followcount();
          followable();
        })
        .catch((error) => {
          console.error(error);
          alert("언팔로우 실패");
        });
    };

    return {
      state,
      onBeforeMount,
      following,
      unfollow,
    };
  },
});
</script>
