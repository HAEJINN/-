import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./lib/store";
import { Quasar } from "quasar";
import quasarUserOptions from "./quasar-user-options";

createApp(App)
  .use(Quasar, quasarUserOptions)
  .use(store)
  .use(router)
  .mount("#app");
