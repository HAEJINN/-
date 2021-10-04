<template>
  <q-dialog>
    <q-card class="picture-card q-dialog-plugin">
      <div class="picture-img-section">
        <q-btn
          class="card-close bg-white"
          v-close-popup
          flat
          round
          dense
          icon="close"
        />
        <q-carousel v-model="slide" :fullscreen="fullscreen">
          <q-carousel-slide :name="1" class="carousel-slide">
            <q-img :src="malang" fit="contain" class="carousel-image"></q-img>
          </q-carousel-slide>
          <template v-slot:control>
            <q-carousel-control position="bottom-right" :offset="[18, 18]">
              <q-btn
                push
                round
                dense
                color="white"
                text-color="primary"
                :icon="fullscreen ? 'fullscreen_exit' : 'fullscreen'"
                @click="fullscreen = !fullscreen"
              />
            </q-carousel-control>
          </template>
        </q-carousel>
      </div>
      <q-card-section class="column">
        <div class="row justify-center items-bottom">
          <div class="picture-title text-h4 text-weight-bolder q-mr-md">
            {{ title }}
          </div>
          <div class="text-right text-h6 text-weight-medium q-ml-md self-end">
            {{ name }}
          </div>
          <q-btn class="self-end" flat round dense icon="home"></q-btn>
        </div>
      </q-card-section>

      <q-card-section>
        <div class="q-ml-lg q-mt-sm q-mb-sm text-h6">작품 소개</div>
        <div class="q-ml-lg q-mr-lg">{{ description }}</div>
      </q-card-section>
      <q-card-section class="q-mt-lg">
        <div class="row items-center">
          <div class="col-7"></div>
          <div v-if="price !== null" class="col-3 text-center">
            {{ price }} 원
          </div>
          <q-btn
            v-if="price !== null"
            class="bg-dark col-2"
            text-color="white"
            label="구매하기"
            @click="mvPurchase"
          ></q-btn>

          <div v-else>이미 판매 완료된 상품입니다</div>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>
<script>
import "../../styles/picture.scss";
import { defineComponent, reactive, ref } from "vue";

export default defineComponent({
  name: "picture",
  props: {
    title: String,
    name: String,
    description: String,
    price: String,
  },
  setup(props, { emit }) {
    const mvPurchase = () => {
      emit("mvPurchase");
    };
    return {
      malang: require("@/assets/mypage/malang.png"),
      slide: ref(1),
      fullscreen: ref(false),

      mvPurchase,
    };
  },
});
</script>
