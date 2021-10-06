<template>
  <div class="row">
    <picture-comp></picture-comp>
    <picture-comp></picture-comp>
    <picture-comp></picture-comp>
    <picture-comp></picture-comp>
    <picture-comp></picture-comp>
    <!-- <picture-comp
      v-for="(collection, idx) in collection_list"
      :key="idx"
      :collection="collection"
    ></picture-comp> -->
  </div>
</template>
<script>
import { defineComponent, reactive, ref } from "vue";
import { useStore } from "vuex";
import PictureComp from "../components/picture-comp.vue";

export default defineComponent({
  name: "mypage-collection-picture",
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
  //       .dispatch("root/request_collection_picture")
  //       .then((response) => {
  //         state.collection_list = response.data;
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
