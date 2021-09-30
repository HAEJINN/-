<template>
  <div>
    <div class="column items-center">
      <div class="text-h5 q-my-md">사진 올리기</div>
      <div>
        <label for="name">사진제목</label>
        <input
          type="text"
          id="name"
          placeholder="사진제목"
          v-model="state.picture.name"
        />
      </div>
      <div>
        <label for="description">사진설명</label>
        <input
          type="text"
          id="description"
          placeholder="사진설명"
          v-model="state.picture.description"
        />
      </div>
      <div>
        <label for="price">가격</label>
        <input
          type="number"
          id="price"
          placeholder="가격"
          v-model="state.picture.price"
        />
      </div>
      <div>
        <label for="chooseFile">사진첨부</label>
        <input
          type="file"
          id="chooseFile"
          name="chooseFile"
          accept="image/*"
          @change="loadf"
        />
      </div>
      <div>
        <img src="" class="preview" />
      </div>
      <button @click="clickUpload()">올리기</button>
    </div>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import { reactive } from "vue";
import store from "../../lib/store";

export default defineComponent({
  name: "addpic",
  setup() {
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

      console.log(file.files[0]);

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
            id: userinfo.email,
            // 지갑주소,
            name: state.picture.name,
            description: state.picture.description,
            price: state.picture.price,
            cid: state.cid,
          };
          console.log(data);

          // store
          // .dispatch("", data)
          // .then((response)=>{
          //   console.log(response);
          //   alert('사진이 등록되었습니다.');
          //   router.push("/mypage");
          // })
          // .catch((error)=>{
          //   console.error(error);
          // })
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
