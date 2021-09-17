import { createWebHistory, createRouter } from "vue-router";

const routes = [
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     홈     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
  {
    path: "/",
    name: "Home",
    component: () => import("../views/main/main.vue"),
  },
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     유저     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
  {
    path: "/join",
    name: "join",
    component: () => import("../views/user/join.vue"),
  },
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     배너     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
  {
    path: "/najakhwa",
    name: "info",
    component: () => import("../views/banner/info.vue"),
  },
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     거래     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
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
