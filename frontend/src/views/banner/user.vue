<template>
  <div>
    <div class="q-my-lg q-py-lg bg-accent">
      <div class="text-h4">컬렉션 모아보기</div>
      <div class="q-mt-md">나작화 회원들의 컬렉션을 보여드려요</div>
      <div class="q-mt-xs">누르면 컬렉션으로 이동해요!</div>
    </div>
    <div class="row justify-center q-mb-lg">
      <div class="col-lg-8 col-xs-12">
        <div class="row justify-around">
          <UserComp></UserComp>
          <UserComp></UserComp>
          <UserComp></UserComp>
          <UserComp></UserComp>
          <UserComp></UserComp>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import UserComp from "../banner/components/user-comp.vue";
export default defineComponent({
  name: "user",
  components: {
    UserComp,
  },
});
</script>
