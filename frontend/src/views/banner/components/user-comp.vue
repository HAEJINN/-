<template>
  <div class="user-comp col-lg-3 col-xs-6" @click="mvUserCollection">
    <div class="q-ma-sm bg-accent">
      <img src="../../../assets/profile.png" />
    </div>
    <div>{{ user.name }}</div>
  </div>
</template>

<script>
import { defineComponent, reactive, ref } from "vue";
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
    const mvUserCollection = () => {
      router.push({
        name: "feed",
        params: {
          user_id: props.user.id,
        },
      });
    };
    return {
      mvUserCollection,
    };
  },
});
</script>
