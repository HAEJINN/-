<template>
  <div>
    <div class="row">
      <Followitem
        v-for="(follow, idx) in followers"
        :key="idx"
        :follow="follow"
      ></Followitem>
    </div>
    <div class="row justify-center">
      <div v-if="followers.length == 0" class="text-h5 q-py-xl">
        팔로워가 없습니다
      </div>
    </div>
  </div>
</template>
<script>
import { defineComponent, onBeforeMount, reactive, ref } from "vue";
import Followitem from "../components/followitem.vue";
import { useStore } from "vuex";

export default defineComponent({
  name: "follower",
  components: {
    Followitem,
  },
  setup() {
    const store = useStore();
    const state = reactive({});
    const followers = ref([]);

    onBeforeMount(() => {
      const userinfo = JSON.parse(localStorage.getItem("userInfo"));
      store
        .dispatch("root/request_followers", userinfo.id)
        .then((response) => {
          followers.value = response.data;
          // console.log(followers.value);
        })
        .catch((error) => {
          console.error(error);
        });
    });

    return {
      state,
      onBeforeMount,
      followers,
    };
  },
});
</script>
