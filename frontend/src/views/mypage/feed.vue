<template>
  <div>
    <div class="row justify-center items-center bg-secondary q-py-lg">
      <div class="col-lg-2 col-xs-4">
        <div class="feed-profile"></div>
      </div>
      <div class="col-lg-4 col-xs-7">
        <div class="row justify-center q-mb-md">
          <div class="col-5">
            <div class="text-h6 text-bold">팔로워</div>
            <div class="text-h6">100</div>
          </div>
          <div class="col-5">
            <div class="text-h6 text-bold">팔로잉</div>
            <div class="text-h6">51</div>
          </div>
        </div>
        <div class="follow-btn">팔로우</div>
      </div>
    </div>
    <div class="bg-accent q-py-sm q-mb-sm feed-user">📷 ooo님의 화실 📷</div>
    <div class="row justify-center">
      <div class="row justify-center col-lg-8 col-xs-11">
        <Picture></Picture>
        <Picture></Picture>
        <Picture></Picture>
        <Picture></Picture>
        <Picture></Picture>
      </div>
    </div>
  </div>
</template>
<script>
import "../../styles/mypage.scss";
import { defineComponent } from "vue";
import Picture from "../mypage/components/picture.vue";

export default defineComponent({
  name: "feed",
  components: {
    Picture,
  },
});
</script>
