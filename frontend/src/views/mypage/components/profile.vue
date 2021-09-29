<template>
  <div class="bg-accent">
    <div class="row justify-center">
      <div class="col-lg-2 col-xs-8 q-py-sm">
        <q-responsive :ratio="1">
          <div class="profile-image">
            <div class="modify-profile"></div>
          </div>
        </q-responsive>
      </div>
    </div>
    <span class="text-h6">ooo님 환영합니다</span>
  </div>
</template>
<script lang="ts">
import "../../../styles/mypage.scss";
import { defineComponent } from "vue";
export default defineComponent({});
</script>
