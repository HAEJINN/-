<template>
  <div class="my-font">
    <Nav></Nav>
    <router-view></router-view>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import Nav from "@/views/nav.vue";
import "../src/styles/app.css";
export default defineComponent({
  name: "App",
  components: {
    Nav,
  },
});
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
