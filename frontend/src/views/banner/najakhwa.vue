<template>
  <div>
    <div class="najakhwa-banner row justify-center items-center bg-accent">
      <q-img
        class="banner-najakhwa-logo"
        src="../../assets/Najakhwa_logo_red.png"
        fit="contain"
      />
      <div class="text-h4 text-bold">나만의 작은 화실</div>
    </div>
    <div class="text-h6 q-my-md">
      <div class="col-lg-6 col-xs-12">나만의 사진을 공유하고</div>
      <div class="col-lg-6 col-xs-12">안전하게 거래 할 수 있는 플랫폼</div>
    </div>
    <div class="row justify-center items-center q-my-sm">
      <div class="provider user bg-secondary">
        <div class="text-h6 text-bold q-mb-lg">공급자</div>
        <div class="provider-inner">
          <q-img
            class="provider-image"
            src="../../assets/banner/us.png"
            :ratio="1"
            fit="contain"
          />
          <div>자신 그리고 타인, 우리 모두 될 수 있습니다.</div>
        </div>
      </div>
      <div class="consumer user bg-secondary">
        <div class="text-h6 text-bold q-mb-lg">수요자</div>
        <div class="consumer-inner justify-center items-center">
          <div class="q-py-lg">
            <div>잡지에 올릴 사진을 찾는</div>
            <div class="text-h5">에디터</div>
          </div>
          <div class="q-py-lg">
            <div>온라인 포스팅의 관련 이미지가 필요한</div>
            <div class="text-h5">블로거</div>
          </div>
          <div class="q-py-lg">
            <div>개인 취향의 사진을 수집하는</div>
            <div class="text-h5">수집가</div>
          </div>
        </div>
      </div>
    </div>
    <div class="najakhwa-banner q-pt-lg q-pb-xl bg-accent">
      <span class="text-h4">제공 서비스</span>
      <div class="row justify-center">
        <div class="col-lg-2 col-xs-3">
          <div class="upload"></div>
          <div class="text-subtitle1">사진 업로드</div>
        </div>
        <div class="col-lg-2 col-xs-3">
          <div class="picture"></div>
          <div class="text-subtitle1">사진 구경</div>
        </div>
        <div class="col-lg-2 col-xs-3">
          <div class="purchase"></div>
          <div class="text-subtitle1">사진 구매</div>
        </div>
        <div class="col-lg-2 col-xs-3">
          <div class="sell"></div>
          <div class="text-subtitle1">사진 판매</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import "../../styles/banner.scss";
import { defineComponent } from "vue";

export default defineComponent({
  setup() {
    return {};
  },
});
</script>
