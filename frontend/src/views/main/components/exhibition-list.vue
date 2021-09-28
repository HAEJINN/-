<template>
  <div class="q-pa-sm">
    <div class="text-center text-h5">최근 전시회</div>
    <q-carousel swipeable animated arrows v-model="slide" infinite>
      <q-carousel-slide
        :name="1"
        img-src="https://cdn.quasar.dev/img/mountains.jpg"
      >
        <div class="absolute-bottom bg-accent text-center">
          <div class="text-h5 q-py-sm">전시회명</div>
          <div class="text-subtitle1">작가명</div>
        </div>
      </q-carousel-slide>
      <q-carousel-slide
        :name="2"
        img-src="https://cdn.quasar.dev/img/parallax1.jpg"
      >
        <div class="absolute-bottom bg-accent text-center">
          <div class="text-h5 q-py-sm">전시회명</div>
          <div class="text-subtitle1">작가명</div>
        </div>
      </q-carousel-slide>
      <q-carousel-slide
        :name="3"
        img-src="https://cdn.quasar.dev/img/parallax2.jpg"
      >
        <div class="absolute-bottom bg-accent text-center">
          <div class="text-h5 q-py-sm">전시회명</div>
          <div class="text-subtitle1">작가명</div>
        </div>
      </q-carousel-slide>
      <q-carousel-slide
        :name="4"
        img-src="https://cdn.quasar.dev/img/quasar.jpg"
      >
        <div class="absolute-bottom bg-accent text-center">
          <div class="text-h5 q-py-sm">전시회명</div>
          <div class="text-subtitle1">작가명</div>
        </div>
      </q-carousel-slide>
    </q-carousel>
  </div>
</template>
<script>
import { ref } from "vue";

export default {
  setup() {
    return {
      slide: ref(1),
    };
  },
};
</script>
