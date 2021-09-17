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
      <q-toolbar-title> 나만의 작은 화실 </q-toolbar-title>
      <q-btn flat round dense icon="sim_card" class="q-mr-xs" />
      <q-btn flat round dense icon="people" @click="dialog_login = true" />
      <login-dialog v-model="dialog_login" />
    </q-toolbar>
  </div>
</template>

<script>
import { ref } from "vue";
import { useRouter } from "vue-router";
import LoginDialog from "@/views/user/login-dialog";

export default {
  components: {
    LoginDialog,
  },
  setup() {
    return {
      dialog_login: ref(false),
    };
  },
};
</script>
