<template>
  <div>
    <q-toolbar>
      <q-btn dense flat round icon="menu">
        <q-menu>
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
        </q-menu>
      </q-btn>
      <q-toolbar-title> 나만의 작은 화가 </q-toolbar-title>
      <q-btn flat round dense icon="sim_card" class="q-mr-xs" />
      <q-btn flat round dense icon="people" @click="moveJoin" />
    </q-toolbar>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
  setup() {
    const router = useRouter();

    const moveJoin = () => {
      router.push("/join");
    };

    return {
      moveJoin,
    };
  },
});
</script>
