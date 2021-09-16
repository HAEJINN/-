<template>
  <div>
    <Banner></Banner>
    <Exhibition></Exhibition>
    <Item></Item>
    <div class="col-sm-12 col-md-8">신규 작가입니다</div>
    <Calendar></Calendar>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import Banner from "@/views/main/components/banner.vue";
import Item from "@/views/main/components/item.vue";
import Exhibition from "@/views/main/components/exhibition.vue";
import Calendar from "@/views/main/components/calendar.vue";
export default defineComponent({
  name: "Main",
  components: {
    Banner,
    Exhibition,
    Item,
    Calendar,
  },
});
</script>
