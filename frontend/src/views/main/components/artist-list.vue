<template>
  <div class="q-pa-sm q-mt-md q-mb-xl">
    <div class="text-center text-h5">최근 가입한 사진가</div>
    <div class="row">
      <ArtistComp></ArtistComp>
      <ArtistComp></ArtistComp>
      <ArtistComp></ArtistComp>
      <ArtistComp></ArtistComp>
    </div>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import ArtistComp from "../components/artist-comp.vue";

export default defineComponent({
  name: "artist-list",
  components: {
    ArtistComp,
  },
});
</script>
