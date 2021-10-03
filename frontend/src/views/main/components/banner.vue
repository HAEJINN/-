<template>
  <div class="q-pa-sm">
    <div class="q-gutter-md">
      <q-carousel
        v-model="slide"
        transition-prev="scale"
        transition-next="scale"
        swipeable
        animated
        control-color="dark"
        navigation
        padding
        arrows
        height="300px"
        class="bg-accent text-white shadow-1 rounded-borders"
      >
        <q-carousel-slide name="style" class="column no-wrap flex-center">
          <div class="q-mt-md text-center">
            <div class="text-dark text-h6 q-py-md">나만의 작은 화실 소개</div>
            <q-btn color="positive" @click="moveInfo">바로가기</q-btn>
          </div>
        </q-carousel-slide>
        <q-carousel-slide name="tv" class="column no-wrap flex-center">
          <div class="q-mt-md text-center">
            <div class="text-dark text-h6 q-py-md">컬렉션소개</div>
            <q-btn color="positive" @click="moveCollection">바로가기</q-btn>
          </div>
        </q-carousel-slide>
        <q-carousel-slide name="layers" class="column no-wrap flex-center">
          <div class="q-mt-md text-center">
            <div class="text-dark text-h6 q-py-md">사진 모아보기</div>
            <q-btn color="positive" @click="mvpicture">바로가기</q-btn>
          </div>
        </q-carousel-slide>
        <q-carousel-slide name="map" class="column no-wrap flex-center">
          <div class="q-mt-md text-center">
            <div class="text-dark text-h6 q-py-md">컬렉션 모아보기</div>
            <q-btn color="positive" @click="mvcollections">바로가기</q-btn>
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
      router.push("/collection");
    };
    const mvpicture = () => {
      router.push("/picture");
    };
    const mvcollections = () => {
      router.push("/collections");
    };

    return {
      slide,
      moveInfo,
      moveCollection,
      mvpicture,
      mvcollections,
    };
  },
};
</script>
