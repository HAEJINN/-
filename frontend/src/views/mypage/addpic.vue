<template>
  <div>
    <div>
      <div class="col-12 colunm items-center bg-accent q-py-lg q-mb-lg">
        <div class="text-h5 q-my-md">사진 올리기</div>
        <div class="q-my-sm">올리고 싶은 사진을 올려주세요</div>
        <div class="q-my-sm">
          사진의 제목과 사진에 대한 간단한 설명이 필요합니다
        </div>
        <div class="q-my-sm">원하시는 상품의 판매가도 적어주세요</div>
      </div>
      <div>
        <label for="name" class="q-mr-sm">사진제목</label>
        <input
          type="text"
          id="name"
          placeholder="제목 입력"
          v-model="state.picture.name"
        />
      </div>
      <div class="row items-center justify-center q-my-md">
        <label for="description" class="q-mr-sm">사진설명</label>
        <textarea
          type="text"
          id="description"
          placeholder="사진에 대한 설명 작성"
          rows="3"
          v-model="state.picture.description"
        />
      </div>
      <div class="q-my-md">
        <label for="price" class="q-mr-sm">사진가격</label>
        <input
          type="number"
          id="price"
          placeholder="원하는 판매가격"
          v-model="state.picture.price"
        />
      </div>
      <div class="q-mt-lg q-mb-md">
        <label for="chooseFile" class="upload-btn q-mr-sm">사진첨부</label>
        <input
          type="file"
          id="chooseFile"
          name="chooseFile"
          accept="image/*"
          @change="loadf"
          style="display: none"
        />
      </div>
      <div class="q-my-sm">
        <img src="" class="preview" />
      </div>
      <button @click="clickUpload()" class="q-mt-xs q-mb-xl">올리기</button>
    </div>
  </div>
</template>
<script>
import "../../styles/mypage.scss";
import { defineComponent } from "vue";
import { reactive } from "vue";
import { useStore } from "vuex";

export default defineComponent({
  name: "addpic",
  setup() {
    const store = useStore();
    const state = reactive({
      picture: {
        name: "",
        description: "",
        price: "",
        file: null,
        src: "",
      },
      cid: "",
    });
    const loadf = () => {
      var file = document.getElementById("chooseFile");
      var preview = document.querySelector(".preview");
      preview.src = URL.createObjectURL(file.files[0]);

      preview.style.width = "60%";
      preview.style.height = "60%";
      preview.style.maxHeight = "500px";
    };

    const clickUpload = () => {
      // pinata통해 cid얻기
      const image = document.getElementById("chooseFile").files[0];
      store
        .dispatch("root/request_pinata", image)
        .then((response) => {
          console.log(response);
          state.cid = response.data.IpfsHash;

          // cid 받아왔으면 데이터 합쳐 보내기
          const userinfo = JSON.parse(localStorage.getItem("userInfo"));
          const data = {
            id: userinfo.id,
            // 지갑주소,
            name: state.picture.name,
            description: state.picture.description,
            price: state.picture.price,
            cid: state.cid,
          };

          // store
          //   .dispatch("root/request_picupload", data)
          //   .then((response) => {
          //     console.log(response);
          //     alert('사진이 등록되었습니다.');
          //     router.push("/mypage");
          //   })
          //   .catch((error) => {
          //     console.error(error);
          //   });
        })
        .catch((error) => {
          console.error(error);
        });
    };

    return {
      loadf,
      clickUpload,
      state,
    };
  },
});
</script>
