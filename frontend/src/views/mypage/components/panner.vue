<template>
  <div class="q-mt-md">
    <div class="row justify-center">
      <div class="col-lg-10 col-xs-12">
        <q-tabs
          v-model="tab"
          dense
          class="bg-brown-1"
          active-color="dark bg-primary"
          indicator-color="dark"
          align="justify"
          narrow-indicator
        >
          <q-tab name="wallet" label="지갑" />
          <q-tab name="following" label="팔로잉" />
          <q-tab name="follower" label="팔로워" />
        </q-tabs>

        <q-separator />

        <q-tab-panels v-model="tab" animated>
          <q-tab-panel name="wallet">
            <Wallet></Wallet>
          </q-tab-panel>
          <q-tab-panel name="following">
            <Following></Following>
          </q-tab-panel>
          <q-tab-panel name="follower">
            <Follower></Follower>
          </q-tab-panel>
        </q-tab-panels>
      </div>
    </div>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import { ref } from "vue";
import Wallet from "../components/wallet.vue";
import Following from "../components/following.vue";
import Follower from "../components/follower.vue";
import "../../../styles/mypage.scss";

export default defineComponent({
  name: "panner",
  components: {
    Wallet,
    Following,
    Follower,
  },
  setup() {
    return {
      tab: ref("wallet"),
    };
  },
});
</script>
