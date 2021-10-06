<template>
  <div class="artist-comp col-lg-3 col-xs-6" @click="mvUserCollection">
    <q-avatar class="q-my-md" color="primary" size="160px">
      <img src="../../../assets/profile.png" />
    </q-avatar>
    <div>{{ user.name }}</div>
  </div>
</template>
<script>
import "../../../styles/main.scss";
import { defineComponent } from "vue";

export default defineComponent({
  name: "artist-comp",
  props: {
    user: {
      type: Object,
    },
  },
  setup(props) {
    const mvUserCollection = () => {
      alert(props.user.id);
    };

    return {
      mvUserCollection,
    };
  },
});
</script>
