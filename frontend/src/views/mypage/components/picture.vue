<template>
  <div class="row">
    <picture-comp
      v-for="(collection, idx) in state.collection_list"
      :key="idx"
      :collection="collection"
    ></picture-comp>
  </div>
</template>
<script>
import { defineComponent, reactive, ref, onBeforeMount } from "vue";
import { useStore } from "vuex";
import PictureComp from "../components/picture-comp.vue";

export default defineComponent({
  name: "mypage-collection-picture",
  components: {
    PictureComp,
  },
  setup() {
    const store = useStore();
    const state = reactive({
      collection_list: ref([]),
    });
    onBeforeMount(() => {
      const userinfo = JSON.parse(localStorage.getItem("userInfo"));
      store
        .dispatch("root/request_collection_picture", userinfo.id)
        .then((response) => {
          // console.log(response);
          state.collection_list = response;
        })
        .catch((error) => {
          console.log(error);
        });
    });

    return {
      state,
      onBeforeMount,
    };
  },
});
</script>
