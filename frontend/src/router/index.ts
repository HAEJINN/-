import { createWebHistory, createRouter } from "vue-router";

const routes = [
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     홈     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
  {
    path: "/",
    name: "main",
    component: () => import("../views/main/main.vue"),
  },
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     유저     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
  {
    path: "/mypage",
    name: "mypage",
    component: () => import("../views/mypage/mypage.vue"),
  },
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     작품     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
  // {
  //   path: "/item",
  //   name: "item",
  //   component: () => import("../views/item/item.vue"),
  // },
  // {
  //   path: "/item/create",
  //   name: "item-create",
  //   component: () => import("../views/item/create.vue"),
  // },
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     배너     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
  {
    path: "/najakhwa",
    name: "najakhwa",
    component: () => import("../views/banner/najakhwa.vue"),
  },
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     컬렉션     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
  {
    path: "/collection",
    name: "collection",
    component: () => import("../views/banner/collection.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
