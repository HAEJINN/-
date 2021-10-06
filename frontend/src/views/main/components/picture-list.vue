<template>
  <div class="q-pa-sm q-my-md">
    <div class="text-center text-h5 q-mb-md">최근 사진</div>
    <div class="row">
      <!-- <PictureComp></PictureComp>
      <PictureComp></PictureComp>
      <PictureComp></PictureComp>
      <PictureComp></PictureComp> -->
      <PictureComp
        v-for="(collection, idx) in collection_list"
        :key="idx"
        :collection="collection"
      ></PictureComp>
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
  setup() {
    const store = useStore();
    const collection_list = ref([]);
    onBeforeMount(() => {
      store
        .dispatch("root/request_latest_picture")
        .then((response) => {
          console.log(response);
          collection_list.value = response;
        })
        .catch((error) => {
          console.error(error);
        });
    });
    return {
      collection_list,
      onBeforeMount,
    };
  },
});
</script>
