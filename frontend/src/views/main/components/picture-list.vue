<template>
  <div class="q-pa-sm q-my-md">
    <div class="text-center text-h5 q-mb-md">최근 사진</div>
    <div class="row">
      <PictureComp></PictureComp>
      <PictureComp></PictureComp>
      <PictureComp></PictureComp>
      <PictureComp></PictureComp>
      <!-- <PictureComp
        v-for="(collection, idx) in state.collection_list"
        :key="idx"
        :collection="collection"
      ></PictureComp> -->
    </div>
  </div>
</template>
<script>
import { defineComponent, reactive, ref, onBeforeMount } from "vue";
import { useStore } from "vuex";
import PictureComp from "../components/picture-comp.vue";

export default defineComponent({
  name: "picture-list",
  components: {
    PictureComp,
  },
  // setup() {
  //   const store = useStore();
  //   const state = reactive({
  //     collection_list: ref([]),
  //   });
  //   onBeforeMount(() => {
  //     store
  //       .dispatch("/root/request_latest_picture")
  //       .then((response) => {
  //         state.collection_list = response;
  //       })
  //       .catch((error) => {
  //         console.log(error);
  //       });
  //   });
  //   return {
  //     state,

  //     onBeforeMount,
  //   };
  // },
});
</script>
