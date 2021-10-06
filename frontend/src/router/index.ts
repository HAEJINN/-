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
  {
    path: "/feed",
    name: "feed",
    props: true,
    component: () => import("../views/mypage/feed.vue"),
  },
  {
    path: "/addpic",
    name: "addpic",
    component: () => import("../views/mypage/addpic.vue"),
  },
  {
    path: "/modify",
    name: "modify",
    component: () => import("../views/mypage/modifyme.vue"),
  },
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     작품     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
  {
    path: "/purchase",
    name: "purchase",
    component: () => import("../views/picture/purchase.vue"),
  },
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     나작화소개     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
  {
    path: "/najakhwa",
    name: "najakhwa",
    component: () => import("../views/banner/najakhwa.vue"),
  },
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     컬렉션소개     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
  {
    path: "/collection",
    name: "collection",
    component: () => import("../views/banner/collection.vue"),
  },
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     컬렉션 모아보기     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
  {
    path: "/collections",
    name: "collections",
    component: () => import("../views/banner/user.vue"),
  },
  /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ     사진 모아보기     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
  {
    path: "/picture",
    name: "picture",
    component: () => import("../views/banner/picture.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
