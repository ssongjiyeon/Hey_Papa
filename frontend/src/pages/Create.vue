<template>
  <div>
    <div class="top">
      <q-btn class="back_btn" @click="goHome" round icon="arrow_back" />
      <div class="logo">Hey PaPa!</div>
    </div>
    <!-- <div style="display:flex; justify-content:center; margin-top:20px; margin-bottom:20px;">
      <img @src="dataUrl" class="show_img" style="border:1px solid black; height:300px;">
    </div>
    <div flat bordered style="width:90%; margin-left:20px;">
      <div style="display:flex;" v-html="editor"></div>
    </div>
    <span>사진첨부</span>
    <div id="q-app">
      <div class="q-pa-md q-gutter-sm">
        <q-editor min-height="5rem"
                  :definitions="definitions"
                  :toolbar="[['insert_img']]"></q-editor>
      </div>
    </div> -->

    <div style="width:90%; margin-left:20px; margin-top:20px; margin-bottom:20px;">
      <div class="text-subitle2" style="height:400px; margin-bottom:20px;">
        <div :style="{ 'background-image': `url(${imageData})` }" @click="choosepicture" style="object-fit:cover;">
          <q-btn
            v-if="!imageData"
            class="placeholder"
            style="width:100%; height:400px;"
            icon="photo_camera"
          />
          <input hidden class="file-input" ref="fileInput" type="file" @input="onSelectFile" />
        </div>
        <q-img :src="imageData" style="cursor: pointer; object-fit:cover;" @click="choosepicture" />
      </div>
    </div>
    <div style="margin-top:4  0px;">
      <!-- <span style="margin-left:20px;">내용</span> -->
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
      <q-input v-model="text" label="ex)#육아#미역국"/>
    </div>
    <div style="display:flex; justify-content: center; margin-top:50px;">
       <q-btn unelevated rounded color="primary" label="게시글 작성" style="width:300px;"/>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
export default {
  el: '#q-app',
  data() {
    return {
      imageData: null,
    }
  },
  methods: {
    onSelectFile() {
      const input = this.$refs.fileInput;
      const files = input.files;
      this.FileImage = files[0];
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
  },
  // data () {
  //   return {
  //     editor: '',
  //     definitions: {
  //       insert_img: {
  //         label: '사진넣기',
  //         icon: 'photo',
  //         handler: this.insertImg 
  //       }
  //     },
  //     dataUrl : ''
  //   }
  // },
  // methods: {
  //   insertImg() { 
  //           const input = document.createElement('input')
  //           input.type = 'file'
  //           input.accept = '.png, .jpg'
  //           let file
  //           input.onchange = _ => {
  //               const files = Array.from(input.files)
  //               file = files[0]
  //               const reader = new FileReader()
  //               reader.onloadend = () => {
  //                   this.dataUrl = reader.result
  //                   this.editor += '<div style="display:flex;"><img style="max-width: 100px; height:100px;" src="' + this.dataUrl + '" /></div>' 
  //               }
  //               reader.readAsDataURL(file)
  //           }
  //           input.click()
  //       },
  // },
  setup(){
    const router = useRouter()
    const model = ref(null)
    const text = ref('')
    function check(){
      console.log("@@@")
      console.log(model)
    }
    function goHome(){
      router.push('home')
    }
    return{
      check,
      goHome,
      text,
      model
    }
  }
} 
</script>

<style scoped>
.top{
  display:flex;
  align-items: center;
  width:100%;
}
.logo{
  width:100%;
  text-align: center;
}
.back_btn{
  background:rgb(86,86,239);
  color:white;
  width:20px;
  height:20px;
  margin:20px 0px 0px 20px;
}
.show_img{
  /* display */
  width:90%;
  /* height: 40vw; */
  /* height:200px; */
}

</style>