<template>
  <div>
    <div class="collection-banner bg-accent column justify-center items-center">
      <div class="text-h3 q-py-lg text-bold">컬렉션이란?</div>
      <div class="text-h4 q-pb-lg">
        📷 나작화에서만 가질 수 있는 개인의 화실 📷
      </div>
      <div class="text-h6 q-pb-sm">
        내가 올린, 구매한 사진들을 볼 수 있어요!
      </div>
      <q-btn class="bg-dark" text-color="white" @click="mvcollections"
        >컬렉션 모아보기로 이동</q-btn
      >
    </div>
    <div class="row items-center justify-center q-mt-sm q-mb-sm">
      <div class="col-lg-4 col-xs-12 q-pa-md">
        <div class="profile-image"></div>
      </div>
      <div class="col-lg-4 col-xs-12 q-pa-md">
        <div class="text-h4 q-pa-md">다른사람의 사진을 구경하고 싶을 땐</div>
        <div class="text-h4 q-pa-md">프로필 사진을 클릭해 보세요!</div>
      </div>
    </div>
    <div class="row items-center justify-center q-mt-sm q-mb-sm">
      <div class="col-lg-4 col-xs-12 q-pa-md">
        <div class="text-h4 q-pa-md">화실에 있는</div>
        <div class="text-h4 q-pa-md">다양한 사진을</div>
        <div class="text-h4 q-pa-md">볼 수 있어요!</div>
      </div>
      <div class="col-lg-4 col-xs-12 q-pa-md">
        <div class="profile-image"></div>
      </div>
    </div>
    <div class="row items-center justify-center q-mt-sm q-mb-sm">
      <div class="col-lg-4 col-xs-12 q-pa-md row justify-center">
        <div class="collection-najakhwa-logo"></div>
      </div>

      <div class="col-lg-4 col-xs-12 q-pa-md">
        <div class="text-h4 q-pa-md">숨겨진</div>
        <div class="text-h4 q-pa-md">나작화 로고를 통해</div>
        <div class="text-h4 q-pa-md">볼 수 있어요!</div>
      </div>
    </div>
  </div>
</template>
<script>
import "../../styles/banner.scss";
import { defineComponent } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "collection",
  setup() {
    const router = useRouter();
    const mvcollections = () => {
      router.push("/collections");
    };

    return {
      mvcollections,
    };
  },
});
</script>
