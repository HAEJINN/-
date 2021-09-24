<template>
  <div>
    <Banner></Banner>
    <ExhibitionList></ExhibitionList>
    <ItemList></ItemList>
    <ArtistList></ArtistList>
    <Calendar></Calendar>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Banner from "@/views/main/components/banner.vue";
import ExhibitionList from "@/views/main/components/exhibition-list.vue";
import ItemList from "@/views/main/components/item-list.vue";
import ArtistList from "@/views/main/components/artist-list.vue";
import Calendar from "@/views/main/components/calendar.vue";

export default defineComponent({
  name: "main",
  components: {
    Banner,
    ExhibitionList,
    ItemList,
    ArtistList,
    Calendar,
  },
});
</script>
