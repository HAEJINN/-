<template>
  <div class="user-comp col-lg-3 col-xs-6" @click="mvUserCollection">
    <div class="q-ma-sm">
      <img :src="state.profileImage" class="usercomp-pic" />
    </div>
    <div>{{ user.name }}</div>
  </div>
</template>

<script>
import { defineComponent, reactive, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import "../../../styles/banner.scss";

export default defineComponent({
  name: "user-comp",
  props: {
    user: {
      type: Object,
    },
  },
  setup(props) {
    const router = useRouter();
    const state = reactive({
      profileImage: "",
    });
    onBeforeMount(() => {
      state.profileImage =
        "https://najakwha.s3.ap-northeast-2.amazonaws.com/" +
        props.user.profileImage;
    });
    const mvUserCollection = () => {
      router.push({
        name: "feed",
        params: {
          user_id: props.user.id,
        },
      });
    };
    return { state, onBeforeMount, mvUserCollection };
  },
});
</script>
