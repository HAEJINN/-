import { createWebHistory, createRouter } from "vue-router";

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("../views/main/main.vue"), // 동적 import
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
