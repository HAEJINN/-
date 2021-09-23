<template>
  <div>
    <Banner></Banner>
    <Exhibition></Exhibition>
    <Item></Item>
    <Artist></Artist>
    <Calendar></Calendar>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import Banner from "@/views/main/components/banner.vue";
import Item from "@/views/main/components/item.vue";
import Exhibition from "@/views/main/components/exhibition.vue";
import Artist from "@/views/main/components/artist.vue";
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
