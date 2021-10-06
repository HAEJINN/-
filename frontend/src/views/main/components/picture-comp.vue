<template>
  <div class="picture-comp col-lg-3 col-xs-6">
    <q-card class="q-ma-sm" @click="openPictureDialog">
      <img src="https://cdn.quasar.dev/img/mountains.jpg" />

      <q-card-section class="column">
        <div class="text-h6 self-start">작품 이름</div>
        <div class="text-subtitle2 self-end">작가</div>
      </q-card-section>
    </q-card>
    <picture-dialog
      v-model="state.picture"
      :title="state.title"
      :name="state.name"
      :description="state.description"
      :price="state.price"
      @mvPurchase="mvPurchase"
    ></picture-dialog>
    <!-- <picture-dialog
      v-model="state.picture"
      :collection="collection"
      @mvPurchase="mvPurchase"
    ></picture-dialog> -->
  </div>
</template>

<script>
import { defineComponent, reactive, ref, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import PictureDialog from "@/views/picture/picture";
import "../../../styles/main.scss";

export default defineComponent({
  name: "picture-comp",
  components: {
    PictureDialog,
  },
  // props: {
  //   collection: {
  //     type: Object,
  //   },
  // },
  setup(props, { emit }) {
    const router = useRouter();
    const mvPurchase = () => {
      router.push("/purchase");
    };

    const state = reactive({
      picture: ref(false),
      title: "MapleStory",
      name: "nexon",
      description:
        "In the game, players travel the 'Maple World', defeating monsters and developing their characters' skills and abilities as is typical in role-playing games. Players can interact with others in many ways, including chatting and trading. Groups of players can band together in parties to hunt monsters and share rewards, and can also form guilds to interact more easily with each other. Players additionally have the option to visit the in-game 'Cash Shop' to purchase a variety of character appearances or gameplay enhancements with real money. In July 2010, the Korean version of the game was completely revised in a patch named the 'Big Bang'. Other versions followed suit and have since received the Big Bang update. Later in the year, the Korean version received the Chaos update which introduced PvP (player-versus-player) and professions to the game. Its sequel, MapleStory 2, was released in July 2015 and features updated 3D graphics and a similar storyline. As of 2020, the game has reached over 180 million registered users worldwide and grossed over $3 billion in lifetime revenue.",
      price: "100,000,000,000,000",
    });

    const openPictureDialog = () => {
      state.picture = true;
    };
    const closePictureDailog = () => {
      state.picture = false;
    };

    return {
      state,

      mvPurchase,

      openPictureDialog,
      closePictureDailog,
    };
  },
});
</script>
<style lang=""></style>
