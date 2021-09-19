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
      </q-page>
    </q-page-container>
  </q-layout>
</template>
<script>
export default {
  name: "register-success",
  setup() {
    return {};
  },
};
</script>
<style lang=""></style>
