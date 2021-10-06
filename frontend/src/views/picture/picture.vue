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
            <q-img
              :src="state.ImageUrl"
              fit="contain"
              class="carousel-image"
            ></q-img>
            <!-- <q-img :src="pictureurl" fit="contain" class="carousel-image"></q-img> -->
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
            {{ collection.name }}
          </div>
          <div class="text-right text-h6 text-weight-medium q-ml-md self-end">
            {{ collection.author }}/{{ collection.userName }}
          </div>
          <q-btn class="self-end" flat round dense>
            <q-img
              src="../../assets/Najakhwa_logo_red.png"
              fit="contain"
              :ratio="1"
              class="picture-najakhwa-logo"
            ></q-img>
          </q-btn>
        </div>
      </q-card-section>

      <q-card-section>
        <div class="q-ml-lg q-mt-sm q-mb-sm text-h6">작품 소개</div>
        <div class="q-ml-lg q-mr-lg">{{ collection.description }}</div>
      </q-card-section>
      <q-card-section class="q-mt-lg">
        <div class="row items-center">
          <div class="col-7"></div>
          <div class="col-3 text-center">{{ collection.price }} 원</div>
          <q-btn
            class="bg-dark col-2"
            text-color="white"
            label="구매하기"
            @click="mvPurchase"
          ></q-btn>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>
<script>
import "../../styles/picture.scss";
import { defineComponent, reactive, ref, onBeforeMount } from "vue";

export default defineComponent({
  name: "picture",
  props: {
    collection: {
      type: Object,
    },
  },
  setup(props, { emit }) {
    const mvPurchase = () => {
      emit("mvPurchase");
    };
    const state = reactive({
      ImageUrl: "https://gateway.pinata.cloud/ipfs/" + props.collection.cid,
    });

    return {
      slide: ref(1),
      fullscreen: ref(false),

      state,

      onBeforeMount,
      mvPurchase,
    };
  },
});
</script>
