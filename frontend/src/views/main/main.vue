<template>
  <div>
    <Banner></Banner>
    <Exhibition></Exhibition>
    <Item></Item>
    <Artist></Artist>
    <Calendar></Calendar>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Banner from "@/views/main/components/banner.vue";
import Item from "@/views/main/components/item-list.vue";
import Exhibition from "@/views/main/components/exhibition-list.vue";
import Artist from "@/views/main/components/artist-list.vue";
import Calendar from "@/views/main/components/calendar.vue";

export default defineComponent({
  name: "Main",
  components: {
    Banner,
    Exhibition,
    Item,
    Artist,
    Calendar,
  },
});
</script>
