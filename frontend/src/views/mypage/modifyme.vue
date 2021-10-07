<template>
  <div>
    <div>
      <div class="col-12 colunm items-center bg-accent q-py-lg q-mb-lg">
        <div class="text-h5 q-my-md">프로필 변경</div>
        <div class="q-my-sm">나의 소개글을 작성할 수 있어요</div>
        <div class="q-my-sm">프로필 사진도 변경할 수 있어요!</div>
      </div>
      <div class="row items-center justify-center q-my-md">
        <label for="description" class="q-mr-sm">자기 소개</label>
        <textarea
          type="text"
          id="description"
          placeholder="소개글 작성"
          rows="3"
          v-model="state.modi.description"
        />
      </div>
      <div class="q-mt-lg q-mb-md">
        <label for="chooseFile" class="upload-btn q-mr-sm"
          >프로필사진첨부</label
        >
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
      <button @click="clickUpload()" class="q-mt-xs q-mb-xl">수정하기</button>
    </div>
  </div>
</template>
<script>
import "../../styles/mypage.scss";
import { defineComponent } from "vue";
import { reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "addpic",
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      modi: {
        description: "",
        file: null,
        src: "",
      },
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
      const userinfo = JSON.parse(localStorage.getItem("userInfo"));
      const formData = new FormData();
      formData.append("description", state.modi.description);
      formData.append("photo", document.getElementById("chooseFile").files[0]);
      const data = {
        jwtToken: userinfo.jwtToken,
        formData: formData,
      };
      console.log(data);
      store
        .dispatch("root/request_modiuser", data)
        .then((response) => {
          console.log(response);
          alert("프로필이 수정되었습니다.");
          const userid = userinfo.id;
          store
            .dispatch("root/request_userinfo_byid", userid)
            .then((response) => {
              const res = response.data;
              res.jwtToken = data.jwtToken;
              localStorage.setItem("userInfo", JSON.stringify(res));
              // console.log(localStorage.getItem("userInfo"));
            })
            .catch((error) => {
              console.log(error);
            });

          router.go(-1);
        })
        .catch((error) => {
          alert("프로필 수정 실패");
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
