<template>
  <div class="column">
    <div class="row justify-center items-center bg-accent">
      <div class="text-h4 q-my-xl">나만의 작은 화실</div>
      <div class="text-h6 q-my-md col-lg-12 col-xs-10">
        사진을 공유하고 안전하게 경매, 거래 할 수 있는 플랫폼
      </div>
    </div>
    <div class="row justify-center items-center q-my-md">
      <div class="col-lg-4 col-xs-10 user bg-secondary">
        <div class="text-h6 text-bold q-my-sm">공급자</div>
        <div>나</div>
        <div>너</div>
        <div>우리</div>
      </div>
      <div class="col-lg-4 col-xs-10 user bg-secondary">
        <div class="text-h6 text-bold q-my-sm">수요자</div>
        <div>잡지에 올릴 사진을 찾는 에디터</div>
        <div>온라인 포스팅의 관련 이미지가 필요한 사람</div>
        <div>개인 취향의 사진을 수집하는 수집가</div>
      </div>
    </div>
    <div class="q-pt-lg q-pb-xl bg-accent">
      <span class="text-h5">제공 서비스</span>
      <div class="row justify-center">
        <div class="col-lg-2 col-xs-6">
          <div class="upload"></div>
          <div>사진 업로드</div>
        </div>
        <div class="col-lg-2 col-xs-6">
          <div class="picture"></div>
          <div>사진 구경</div>
        </div>
        <div class="col-lg-2 col-xs-6">
          <div class="purchase"></div>
          <div>사진 구매</div>
        </div>
        <div class="col-lg-2 col-xs-6">
          <div class="sell"></div>
          <div>사진 판매</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import "../../styles/banner.scss";
import { defineComponent } from "vue";
export default defineComponent({});
</script>
