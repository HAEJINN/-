<template lang="">
  <q-dialog>
    <q-layout
      view="lhh LpR lff"
      container
      style="height: 500px"
      class="bg-grey-3"
    >
      <q-page-container>
        <q-header class="bg-black">
          <q-toolbar>
            <q-btn v-close-popup flat round dense icon="close" />
          </q-toolbar>
        </q-header>
        <q-page class="col">
          <q-file
            v-model="files"
            label="Pick files"
            filled
            multiple
            append
            style="max-width: 300px"
          />
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>

<script>
import { ref } from "vue";
export default {
  name: "register-dialog",
  setup() {
    return {
      files: ref(null),
    };
  },
};
</script>

<style lang=""></style>
