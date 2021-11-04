<template>
  <div style="padding-bottom:100px;">
    <div class="top">
      <q-btn class="back_btn" @click="goHome" dense round icon="arrow_back" />
      <img class="logo" src="../assets/horizon_logo.png">
      <!-- <q-btn class="back_btn" @click="goHome" dense round icon="edit" /> -->
    </div>
    <div style="width:90%; margin-left:20px; margin-top:20px; margin-bottom:20px;">
      <div class="text-subitle2" style="height:400px; margin-bottom:20px;">
        <div :style="{ 'background-image': `url(${imageData})` }" @click="choosepicture" style="object-fit:cover;">
          <q-btn
            v-if="!imageData"
            class="placeholder"
            style="width:100%; height:400px;"
            icon="photo_camera"
          />
          <input hidden class="file-input" ref="fileInput" type="file" @input="onSelectFile()" />
        </div>
        <q-img :src="imageData" style="cursor: pointer; object-fit:cover;" @click="choosepicture()" />
      </div>
    </div>
    <div style="margin-top:4  0px;">
      <div class="q-pa-md" style="width:100%;">
        <q-input
          v-model="text"
          outlined 
          label = "내용을 입력해주세요."
          type="textarea"
          color = blue
        />
      </div>
    </div>
    <div style="width:90%; margin-left:20px;">
      <span>해시태그</span>
      <q-input v-model="hashtag" label="ex)#육아#미역국"/>
    </div>
    <div style="display:flex; justify-content: center; margin-top:40px;">
       <q-btn @click="goWrite" unelevated rounded label="게시글 작성" style="width:300px; color:white; background:rgb(235,137,181);"/>
    </div>
  </div>
</template>

<script>
import { ref,reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
export default {
  el: '#q-app',
  data() {
    return {
      imageData: null,
      text: '',
      hashtag: '',
    }
  },
  methods: {
    onSelectFile() {
      const input = this.$refs.fileInput;
      const files = input.files;
      this.FileImage = files[0];
      console.log(this.FileImage,'@@@@')
      if (files && files[0]) {
        const reader = new FileReader();
        reader.onload = e => {
          this.imageData = e.target.result;
        };
        reader.readAsDataURL(files[0]);
        this.$emit("input", files[0]);
      }
    },
    choosepicture() {
      this.$refs.fileInput.click();
    },
    goHome(){
      this.$router.push('/home')
    },
    goWrite(){
      const hashlist = this.hashtag.split('#').slice(1,)
      const article = {
        content:this.text,
        hashtag:hashlist,
        img:this.imageData,
        user_id:localStorage.getItem('userId')
      }
      this.$store.dispatch('module/writeArticle',article)
        .then((res)=>{
          console.log(res)
        })
      console.log(article,'@@')
      this.$router.push('/detail')
    }
  },
} 
</script>

<style scoped>
.top{
  display:flex;
  align-items: center;
  /* width:100%; */
}
.logo{
  width:50%;
  margin:0px 0px 0px 55px;
}
.back_btn{
  background:rgb(235, 137, 181);
  color:white;
  width:10px;
  height:10px;
  margin:0px 0px 0px 20px;
}
.show_img{
  width:90%;
}

</style>