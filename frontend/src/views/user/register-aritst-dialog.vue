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
        <q-page class="">
          <q-btn color="secondary" label="소개페이지로 돌아가기" />
          <q-btn class="" color="primary" @click="Register('student')">
            <div>미술대학</div>
            <div>재학생 | 졸업생</div>
          </q-btn>
          <q-btn class="" color="primary" @click="Register('artist')">
            <div>개인작가</div>
            <div>신입 예술 작가</div>
          </q-btn>
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>

<script>
import { ref } from "vue";
export default {
  name: "register-aritst-dialog",
  setup(props, { emit }) {
    const Register = (type) => {
      console.log("여기클릭햇어요");
      emit("Register", type);
    };
    return {
      Register,
    };
  },
};
</script>

<style lang=""></style>
