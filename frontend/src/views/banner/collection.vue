<template>
  <div>
    <div class="bg-accent">
      <div class="text-h5 q-py-lg text-bold">컬렉션이란?</div>
      <div class="text-h6 q-pb-lg">📷개인이 가지고 있는 화실📷</div>
      <div class="q-pb-sm">내가 올리거나 구매한 사진을 확인할 수 있다.</div>
      <div class="q-pb-lg">타인의 컬렉션도 구경 가능하다</div>
    </div>
    <div class="text-h6 q-mt-lg q-mb-md">
      테두리로 상품의 판매 상태를 알 수 있어요
    </div>
    <div class="row justify-center q-pt-md q-mb-xl">
      <div class="col-4 q-mx-sm">
        <div class="finishpic"></div>
        <div class="picinfo">판매 완료된 사진</div>
      </div>
      <div class="col-4 q-mx-sm">
        <div class="remainpic"></div>
        <div class="picinfo">판매중인 사진</div>
      </div>
    </div>
    <q-btn class="q-mb-xl" @click="mvcollections">컬렉션 모아보기로 이동</q-btn>
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
