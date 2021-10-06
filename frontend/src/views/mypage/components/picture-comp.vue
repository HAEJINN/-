<template>
  <div class="picture-comp col-lg-3 col-xs-6">
    <q-card class="q-ma-sm" @click="openPictureDialog">
      <img src="https://cdn.quasar.dev/img/mountains.jpg" />
    </q-card>
    <picture-dialog
      v-model="state.picture"
      :collection="collection"
      @mvPurchase="mvPurchase"
    ></picture-dialog>
  </div>
</template>

<script>
import { defineComponent, reactive, ref } from "vue";
import { useRouter } from "vue-router";
import PictureDialog from "@/views/picture/picture";
import "../../../styles/mypage.scss";

export default defineComponent({
  name: "picture-comp",
  components: {
    PictureDialog,
  },
  props: {
    collection: {
      type: Object,
    },
  },
  setup(props, { emit }) {
    const router = useRouter();
    const mvPurchase = () => {
      router.push("/purchase");
    };

    const state = reactive({
      picture: ref(false),
      collection: {},
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
