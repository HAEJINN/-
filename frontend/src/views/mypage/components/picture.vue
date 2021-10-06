<template>
  <div class="row">
    <picture-comp
      v-for="(collection, idx) in collection_list"
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
  props: {
    user_id: Number,
  },
  setup(props, { emit }) {
    console.log(props.user_id);
    const store = useStore();
    const collection_list = ref([]);
    onBeforeMount(() => {
      store
        .dispatch("root/request_collection_picture", props.user_id)
        .then((response) => {
          collection_list.value = response.data;
          console.log(collection_list.value);
        })
        .catch((error) => {
          console.log(error);
        });
    });

    return {
      collection_list,
      onBeforeMount,
    };
  },
});
</script>
