<template>
  <div class="picture-comp col-lg-4 col-xs-12">
    <q-card class="q-ma-sm" @click="openPictureDialog">
      <img src="https://cdn.quasar.dev/img/mountains.jpg" />

      <q-card-section class="column">
        <div class="text-h6 self-start">작품 이름</div>
        <div class="text-subtitle2 self-end">작가</div>
      </q-card-section>
    </q-card>
    <picture-dialog
      v-model="state.picture"
      :title="state.title"
      :name="state.name"
      :description="state.description"
      :price="state.price"
      @mvPurchase="mvPurchase"
    ></picture-dialog>
    <!-- <picture-dialog
      v-model="state.picture"
      :collection="collection"
      @mvPurchase="mvPurchase"
    ></picture-dialog> -->
  </div>
</template>

<script>
import { defineComponent, reactive, ref } from "vue";
import { useRouter } from "vue-router";
import PictureDialog from "@/views/picture/picture";
import "../../../styles/banner.scss";

export default defineComponent({
  name: "picture-comp",
  components: {
    PictureDialog,
  },
  // props: {
  //   collection: {
  //     type: Object,
  //   },
  // },
  setup(props, { emit }) {
    const router = useRouter();
    const mvPurchase = () => {
      router.push("/purchase");
    };

    const state = reactive({
      picture: ref(false),
      title: "maple",
      name: "nexon",
      description: "MapleStory on LALA",
      price: "100,000,000",
    });

    const openPictureDialog = () => {
      state.picture = true;
    };
    const closePictureDailog = () => {
      state.picture = false;
    };
    return {
      state,

      mvPurchase,
      openPictureDialog,
      closePictureDailog,
    };
  },
});
</script>
<style lang=""></style>
