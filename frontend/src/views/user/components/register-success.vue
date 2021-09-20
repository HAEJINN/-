<template lang="">
  <q-layout
    view="lhh LpR lff"
    container
    style="height: 500px"
    class="bg-grey-3"
  >
    <q-page-container>
      <q-header class="bg-black">
        <q-toolbar>
          <q-btn v-close-popup flat round dense icon="close" />
        </q-toolbar>
      </q-header>
      <q-page class="col">
        <div>작가 인증이 왼료되었습니다 축하합니다~!</div>
        <div>
          나작화에 대해 경험하고 <br />
          새로운 예술품들을 만나러 가시죠~!
        </div>
        <q-btn
          style="background: #ff0080; color: white"
          label="나작화"
          @click="Move_Najakhwa"
        />
        <div>
          마이페이지로 이동해서 <br />
          내 작품들을 올리고, 전시회를 열어보세요!
        </div>
        <q-btn
          flat
          style="color: #ff0080"
          label="마이페이지"
          @click="Move_Mypage"
        />
      </q-page>
    </q-page-container>
  </q-layout>
</template>
<script>
import { useRouter } from "vue-router";
export default {
  name: "register-success",
  setup() {
    const router = useRouter();
    const Move_Najakhwa = () => {
      router.push({
        name: "najakhwa",
      });
    };
    const Move_Mypage = () => {
      router.push({
        name: "mypage",
      });
    };
    return {
      Move_Najakhwa,
      Move_Mypage,
    };
  },
};
</script>
<style lang=""></style>
