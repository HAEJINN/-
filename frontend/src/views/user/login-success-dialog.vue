<template lang="">
  <q-dialog>
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
          <div>회원가입 축하합니다~!</div>
          <div>
            나작화에 대해 경험하고 <br />
            새로운 예술품들을 만나러 가시죠~!
          </div>
          <q-btn
            style="background: #ff0080; color: white"
            label="Go"
            @click="MoveBanner_Najakhwa"
          />
          <div>혹시 미술전공이거나 예술작가이신가요?</div>
          <q-btn
            style="background: goldenrod; color: white"
            label="작가인증하기"
            @click="RegisterArtist"
          />
          <div>마이페이지에서도 작가인증을 할 수 있어요!</div>
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>

<script>
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";

export default {
  name: "login-success-dialog",
  setup(props, { emit }) {
    const router = useRouter();
    const MoveBanner_Najakhwa = () => {
      router.push({
        name: "najakhwa",
      });
    };
    const RegisterArtist = () => {
      emit("RegisterArtist");
    };

    return {
      MoveBanner_Najakhwa,
      RegisterArtist,
    };
  },
};
</script>

<style lang=""></style>
