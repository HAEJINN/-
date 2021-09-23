import { createWebHistory, createRouter } from "vue-router";

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("../views/main/main.vue"),
  },
  {
    path: "/join",
    name: "join",
    component: () => import("../views/user/join.vue"),
  },
  {
    path: "/najakhwa",
    name: "info",
    component: () => import("../views/banner/info.vue"),
  },
  {
    path: "/auctionhistory",
    name: "auctionhistory",
    component: () => import("../views/banner/auction.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
