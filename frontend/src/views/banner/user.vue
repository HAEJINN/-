<template>
  <div>
    <div
      class="
        user-banner
        q-mb-lg q-py-lg
        bg-accent
        column
        justify-center
        items-center
      "
    >
      <div class="text-h3 q-mb-xl">컬렉션 모아보기</div>
      <div class="text-h5 q-mt-md">나작화 회원들의 컬렉션을 보여드려요</div>
      <div class="text-h5 q-mt-xs">누르면 컬렉션으로 이동해요!</div>
    </div>
    <div class="row justify-center q-mb-lg">
      <div class="col-lg-8 col-xs-12">
        <div class="row justify-around">
          <UserComp
            v-for="(user, idx) in state.user_list"
            :key="idx"
            :user="user"
          ></UserComp>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { defineComponent, reactive, ref, onBeforeMount } from "vue";
import { useStore } from "vuex";
import UserComp from "../banner/components/user-comp.vue";
export default defineComponent({
  name: "user",
  components: {
    UserComp,
  },
  setup() {
    const store = useStore();
    const state = reactive({
      user_list: ref([]),
    });
    onBeforeMount(() => {
      store
        .dispatch("root/request_random_user")
        .then((response) => {
          state.user_list = response.data;
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
