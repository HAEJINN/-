<template>
  <div>
    <div class="row">
      <Followitem
        v-for="(follow, idx) in followings"
        :key="idx"
        :follow="follow"
      ></Followitem>
    </div>
    <div class="row justify-center">
      <div v-if="followings.length == 0" class="text-h5 q-py-xl">
        팔로잉이 없습니다
      </div>
    </div>
  </div>
</template>
<script>
import { defineComponent, onBeforeMount, reactive, ref } from "vue";
import Followitem from "../components/followitem.vue";
import { useStore } from "vuex";

export default defineComponent({
  name: "following",
  components: {
    Followitem,
  },
  setup() {
    const store = useStore();
    const state = reactive({});
    const followings = ref([]);

    onBeforeMount(() => {
      const userinfo = JSON.parse(localStorage.getItem("userInfo"));
      store
        .dispatch("root/request_followers", userinfo.id)
        .then((response) => {
          followings.value = response.data;
          // console.log(followings.value);
        })
        .catch((error) => {
          console.error(error);
        });
    });

    return {
      state,
      onBeforeMount,
      followings,
    };
  },
});
</script>
