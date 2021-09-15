<template>
  <q-layout view="hHh LpR lFf">
    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />
        <q-toolbar-title> 나만의 작은 화가 </q-toolbar-title>
        <q-btn flat round dense icon="sim_card" class="q-mr-xs" />
        <q-btn flat round dense icon="gamepad" />
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" side="left" bordered>
      <div class="q-pa-sm">
        <q-list>
          <q-item clickable>
            <q-item-section>Menu1</q-item-section>
          </q-item>
          <q-separator />
          <q-item clickable>
            <q-item-section>Menu2</q-item-section>
          </q-item>
          <q-separator />
          <q-item clickable>
            <q-item-section>Menu3</q-item-section>
          </q-item>
          <q-separator />
          <q-item clickable>
            <q-item-section>Menu4</q-item-section>
          </q-item>
        </q-list>
      </div>
    </q-drawer>
  </q-layout>
</template>

<script>
import { ref } from "vue";

export default {
  setup() {
    const leftDrawerOpen = ref(false);

    return {
      leftDrawerOpen,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value;
      },
    };
  },
};
</script>
