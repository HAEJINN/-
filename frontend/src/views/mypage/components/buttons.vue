<template>
  <div class="col q-pt-sm q-pb-md bg-accent">
    <div class="row justify-center">
      <div class="col-lg-6 col-xs-10 q-my-sm">
        <q-btn-group outline unelevated spread>
          <q-btn outline color="brown" label="내 컬렉션" @click="mvfeed" />
          <q-btn outline color="brown" label="작품 추가" @click="addpicture" />
        </q-btn-group>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import "../../../styles/mypage.scss";
import { defineComponent } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
  setup() {
    const router = useRouter();
    const mvfeed = () => {
      const userinfo = JSON.parse(localStorage.getItem("userInfo") || "{}");
      // const userinfo = JSON.parse(localStorage.getItem("userInfo")!);
      console.log(userinfo.id);
      router.push({
        name: "feed",
        params: {
          user_id: userinfo.id,
        },
      });
    };
    const addpicture = () => {
      router.push("/addpic");
    };

    return {
      mvfeed,
      addpicture,
    };
  },
});
</script>
