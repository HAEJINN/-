<template>
  <div>
    <div class="column items-center">
      <div class="text-h5 q-my-md">사진 올리기</div>
      <div>
        <label for="name">사진제목</label>
        <input
          type="text"
          class="form-control"
          id="name"
          placeholder="사진제목"
          v-model="state.picture.name"
        />
      </div>
      <div>
        <label for="desc">사진설명</label>
        <input
          type="text"
          class="form-control"
          id="desc"
          placeholder="사진설명"
          v-model="state.picture.desc"
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

export default defineComponent({
  name: "addpic",
  setup() {
    const state = reactive({
      picture: {
        name: "",
        desc: "",
        file: null,
        src: "",
      },
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
      const formData = new FormData();
      formData.append("name", state.picture.name);
      formData.append("desc", state.picture.desc);
      // formData.append("userid", 유저아이디);
      formData.append("image", document.getElementById("chooseFile").files[0]);
      console.log(state.picture.name);
      console.log(state.picture.desc);
      console.log(document.getElementById("chooseFile").files[0]);
    };

    return {
      loadf,
      clickUpload,
      state,
    };
  },
});
</script>
