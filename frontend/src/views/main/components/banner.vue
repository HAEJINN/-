<template>
  <div class="q-pa-sm">
    <div class="q-gutter-md">
      <q-carousel
        v-model="slide"
        transition-prev="scale"
        transition-next="scale"
        swipeable
        animated
        control-color="white"
        navigation
        padding
        arrows
        height="300px"
        class="bg-secondary text-white shadow-1 rounded-borders"
      >
        <q-carousel-slide name="style" class="column no-wrap flex-center">
          <div class="q-mt-md text-center">
            <div>나만의 작은 화실 소개</div>
            <q-btn @click="moveInfo">바로가기</q-btn>
          </div>
        </q-carousel-slide>
        <q-carousel-slide name="tv" class="column no-wrap flex-center">
          <div class="q-mt-md text-center">
            <div>컬렉션들</div>
            <q-btn @click="moveCollection">바로가기</q-btn>
          </div>
        </q-carousel-slide>
        <q-carousel-slide name="layers" class="column no-wrap flex-center">
          <div class="q-mt-md text-center">
            <div>판매작품/판매완료작품?</div>
            <q-btn>바로가기</q-btn>
          </div>
        </q-carousel-slide>
        <q-carousel-slide name="map" class="column no-wrap flex-center">
          <div class="q-mt-md text-center">
            <div>모하지..</div>
            <q-btn>바로가기</q-btn>
          </div>
        </q-carousel-slide>
      </q-carousel>
    </div>
  </div>
</template>
<script>
import { ref } from "vue";
import { useRouter } from "vue-router";

export default {
  setup() {
    const router = useRouter();
    const slide = ref("style");
    const moveInfo = () => {
      router.push("/najakhwa");
    };
    const moveCollection = () => {
      router.push("/collections");
    };
    return {
      slide,
      moveInfo,
      moveCollection,
    };
  },
};
</script>
