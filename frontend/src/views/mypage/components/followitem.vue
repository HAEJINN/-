<template>
  <div class="col-lg-4 col-xs-12">
    <div class="row justify-between items-center bg-secondary q-ma-xs q-pa-xs">
      <div class="row items-center">
        <div class="user-img"></div>
        <span>{{ follow.name }}</span>
      </div>
      <div class="mvcollection bg-primary" @click="mvcollection">컬렉션</div>
    </div>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import { useRouter } from "vue-router";
import "../../../styles/mypage.scss";

export default defineComponent({
  name: "followitem",
  props: {
    follow: {
      type: Object,
    },
  },
  setup(props) {
    console.log(props.follow.id);
    const router = useRouter();
    const mvcollection = () => {
      router.push({
        name: "feed",
        params: {
          user_id: props.follow.id,
        },
      });
    };

    return {
      mvcollection,
    };
  },
});
</script>
