<template>
  <div class="q-pa-sm q-mt-md q-mb-xl">
    <div class="text-center text-h5">최근 가입한 사진가</div>
    <div class="row">
      <ArtistComp
        v-for="(user, idx) in latest"
        :key="idx"
        :user="user"
      ></ArtistComp>
    </div>
  </div>
</template>
<script>
import { defineComponent, onBeforeMount, reactive, ref } from "vue";
import ArtistComp from "../components/artist-comp.vue";
import { useStore } from "vuex";

export default defineComponent({
  name: "artist-list",
  components: {
    ArtistComp,
  },
  setup() {
    const store = useStore();
    const state = reactive({});
    const latest = ref([]);
    onBeforeMount(() => {
      store
        .dispatch("root/request_latestuser")
        .then((response) => {
          latest.value = response.data;
          // console.log(latest.value);
        })
        .catch((error) => {
          console.error(error);
        });
    });

    return {
      state,
      onBeforeMount,
      latest,
    };
  },
});
</script>
