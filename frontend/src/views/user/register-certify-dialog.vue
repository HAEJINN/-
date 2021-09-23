<template lang="">
  <q-dialog>
    <q-card class="my-card">
      <q-card-section class="bg-primary">
        <q-uploader
          url="/api/upload"
          label="멋진 작품들로 당신을 증명해주세요!"
          multiple
          @uploaded="RegisterSuccess"
        >
        </q-uploader>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import { ref } from "vue";
// import RegisterUploadfiles from "@/views/user/components/register-uploadfiles";

export default {
  name: "register-certify-dialog",
  components: {
    // RegisterUploadfiles,
  },
  setup(props, { emit }) {
    const RegisterSuccess = () => {
      console.log("여기클릭햇음 success");
      emit("RegisterSuccess", true);
    };
    return {
      files: ref(null),
      RegisterSuccess,
    };
  },
};
</script>

<style lang=""></style>
