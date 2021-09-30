<template>
  <div>
    <Banner></Banner>
    <ItemList></ItemList>
    <ArtistList></ArtistList>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Banner from "@/views/main/components/banner.vue";
import ItemList from "@/views/main/components/item-list.vue";
import ArtistList from "@/views/main/components/artist-list.vue";

export default defineComponent({
  name: "main",
  components: {
    Banner,
    ItemList,
    ArtistList,
  },
});
</script>
