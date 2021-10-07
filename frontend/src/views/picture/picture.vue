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
              class="picture-najakhwa-logo q-ml-sm"
              @click="mvuserCollection"
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
            v-if="state.user.id != collection.userId"
            class="bg-dark col-2"
            text-color="white"
            label="구매하기"
            @click="buyItem"
          ></q-btn>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>
<script>
import "../../styles/picture.scss";
import { defineComponent, reactive, ref, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
export default defineComponent({
  name: "picture",
  props: {
    collection: {
      type: Object,
    },
  },
  setup(props, { emit }) {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      user: JSON.parse(localStorage.getItem("userInfo")),
      address: JSON.parse(localStorage.getItem("address")),
      ImageUrl: "https://gateway.pinata.cloud/ipfs/" + props.collection.cid,
    });
    const buyItem = () => {
      var yesno = confirm(
        "구매하시겠습니까? 구매금액은" + props.collection.price + "원 입니다"
      );
      if (yesno) {
        const reuqset_data = {
          amount: "" + props.collection.price,
          fromAddress: state.address,
          tokenId: props.collection.tokenId,
          userId: props.collection.userId,
        };
        store
          .dispatch("root/request_purchase_picture", reuqset_data)
          .then((response) => {
            console.log(response);
            alert("구매에 성공 하였습니다.");
            router.go();
          })
          .catch((error) => {
            console.log(error);
            alert("구매에 실패 하였습니다.");
          });
      } else {
        alert("구매를 취소하였습니다.");
      }
    };
    const mvuserCollection = () => {
      router.push({
        name: "feed",
        params: {
          user_id: props.collection.userId,
        },
      });
    };
    return {
      slide: ref(1),
      fullscreen: ref(false),

      state,

      buyItem,
      mvuserCollection,

      onBeforeMount,
    };
  },
});
</script>
