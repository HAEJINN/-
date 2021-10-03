<template>
  <div>
    <Banner></Banner>
    <PictureList></PictureList>
    <ArtistList></ArtistList>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Banner from "@/views/main/components/banner.vue";
import PictureList from "@/views/main/components/picture-list.vue";
import ArtistList from "@/views/main/components/artist-list.vue";

export default defineComponent({
  name: "main",
  components: {
    Banner,
    PictureList,
    ArtistList,
  },
});
</script>
