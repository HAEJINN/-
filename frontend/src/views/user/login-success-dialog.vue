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
            @click="move_banner_najakhwa"
          />
          <div>혹시 미술전공이거나 예술작가이신가요?</div>
          <q-btn
            style="background: goldenrod; color: white"
            label="작가인증하기"
            @click="RegisterArtist"
          />
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
  setup() {
    const router = useRouter();
    const move_banner_najakhwa = () => {
      router.push({
        name: "najakhwa",
      });
    };
    const RegisterArtist = () => {
      console.log("register");
    };

    return {
      move_banner_najakhwa,
      RegisterArtist,
    };
  },
});
</script>

<style lang=""></style>
