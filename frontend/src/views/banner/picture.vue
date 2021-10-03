<template>
  <div>
    <div class="q-my-lg q-py-lg bg-accent">
      <div class="text-h4">사진 모아보기</div>
      <div class="q-mt-md">나작화에 올라와 있는 사진 중 몇개를 보여드려요</div>
    </div>
    <div class="row justify-center">
      <div class="col-lg-8 col-xs-12">
        <q-tabs
          v-model="tab"
          dense
          class="bg-brown-1"
          active-color="dark bg-primary"
          indicator-color="dark"
          align="justify"
          narrow-indicator
        >
          <q-tab name="remainpic" label="판매중인 사진" />
          <q-tab name="finishpic" label="판매완료 사진" />
        </q-tabs>

        <q-separator />

        <q-tab-panels v-model="tab" animated>
          <q-tab-panel name="remainpic">
            <Remainpic></Remainpic>
          </q-tab-panel>
          <q-tab-panel name="finishpic">
            <Finishpic></Finishpic>
          </q-tab-panel>
        </q-tab-panels>
      </div>
    </div>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import { ref } from "vue";
import Remainpic from "../banner/components/remainpic.vue";
import Finishpic from "../banner/components/finishpic.vue";
import "@/styles/banner.scss";

export default defineComponent({
  name: "picture",
  components: {
    Remainpic,
    Finishpic,
  },
  setup() {
    return {
      tab: ref("remainpic"),
    };
  },
});
</script>
