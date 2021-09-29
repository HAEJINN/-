<template>
  <div class="col q-py-md bg-accent">
    <q-btn unelevated rounded color="primary" label="작가인증" />
    <div class="row justify-center q-my-sm">
      <q-btn-group outline unelevated>
        <q-btn outline color="brown" label="내 컬렉션" />
        <q-btn outline color="brown" label="작품 추가" />
        <q-btn outline color="brown" label="전시회 등록" />
      </q-btn-group>
    </div>
  </div>
</template>
<script lang="ts">
import "../../../styles/mypage.scss";
import { defineComponent } from "vue";
export default defineComponent({});
</script>
