<template>
<head>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
</head>
  <div class="q-pa-xs row items-start card-box" v-for="para in paras" :key="para.id"
  >
    <q-card class="my-card" flat bordered
    >

      <q-card-section>
        <div class="text-overline text-orange-9">날짜</div>
        <!-- 프로필 박스 -->
        <div class="profile-box">
          <q-responsive :ratio="1">
            <img :src="para.profileUrl" alt="">
          </q-responsive>
          <div class="text-h6 q-mt-sm q-mb-sm q-ml-sm">{{para.name}}</div>
        </div>

        <div :class="extended ? 'more-box' : 'text-box'" transition: fade>
          <div >
            {{ para.content }}
          </div>
        </div>
        <div class="details-box" v-if="para.content.length >= 265">
          <a @click="extended = !extended" v-show="!extended">자세히</a>
          <a @click="extended = !extended" v-show="extended">닫기</a>
        </div>
        <!-- <q-menu
        transition: fade>
        <div v-show="extended">
          <q-card-section class="more-content">
            <div>
            {{ para.content }}
            </div>
          </q-card-section>

        </div>
      </q-menu> -->
      </q-card-section>

      <q-img
        :src="para.imgUrl"
      />

      <q-card-actions>
        <button
        class="fas fa-heart heart-button"
        @click="getHeart"
        :style="heart ? 'color: red': 'color: silver'"/>
        <q-space />

        <q-btn
          label="댓글"
          color="grey"
          round
          flat
          dense
          :icon="expanded ? 'keyboard_arrow_up' : 'keyboard_arrow_down'"
          @click="expanded = !expanded"


        />
      </q-card-actions>

      <q-slide-transition>
        <div v-show="expanded">
          <q-separator />
          <q-card-section class="text-subitle2" v-for="reply in para.reply" :key='reply'>
            {{ reply }}
          </q-card-section>
          <!-- 댓글달기 박스 -->
          <div class="reply-box">
            <input type="text" class="reply-input">
            <button>댓글달기</button>
          </div>

        </div>
      </q-slide-transition>
    </q-card>
  </div>
    <div class="white-space">
      끝
    </div>
</template>

<script>
import { ref } from 'vue'
export default {
  setup(){
    const paras = [
      {
        id:1,
        name: "튼튼이 아빠",
        profileUrl: "https://ifh.cc/g/0nA9O0.png",
        imgUrl: "https://ifh.cc/g/sOxIcm.png",
        content: "첫 게시글ffffffff fffffffffffffff fffffffff fffffffff ffffff fffff fffff ffffff ffff fsdfsfsd fsdfsdfsdf  sdfsdfsdfs dfsdfsdfsd asdfasdfasdfasdf asdfasdf  asdfsdfdf adsfs asdf;lkajs;dlfk s;djasdlkj;fl ;alksdj;flkas f;ldskajsl;dkfj f;lksdajf;lskdfja a;sldkfj;lk fjdakl;sdfdddd",
        like: "5",
        reply: ['안녕', '안녕1'],

      },
      {
        id:2,
        name: "씩씩이 아빠",
        profileUrl: "https://ifh.cc/g/0nA9O0.png",
        imgUrl: "https://lh3.googleusercontent.com/proxy/AYXPov8m6oqrerd8Dq5aRiclau1aPiDjpqeLRD_sjn4uDjcQ5hXljyXTxTgOP-OivR7WVs88eS_wyxQ_toJW2tn1dysm6jYgMr4fVO68f4kKB6O5yxJ3D1g",
        content: "우리부장님^^",
        like: "5",
        reply: ['안녕', '안녕1'],

      },
      {
        id:2,
        name: "씩씩이 아빠",
        profileUrl: "https://ifh.cc/g/0nA9O0.png",
        imgUrl: "",
        content: "우리부장님^^",
        like: "5",
        reply: ['안녕', '안녕1'],

      }
    ]
    const heart = ref(false)
    const getHeart = () => {
      heart.value = !heart.value
      console.log(heart.value)
      }
    console.log(paras[0].content.length)

    return {
      paras,
      expanded: ref(false),
      extended: ref(false),
      getHeart,
      heart,
      lorem: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'

    }

  }
}
</script>

<style scoped>

.my-card {
  width: 100%;
}
.card-box {
  width: 100%;
  min-width: 30px;
  overflow: scroll;
}

.text-box {
  display: -webkit-box;
  min-width: 300px;
  width: inherit;
  white-space: normal;
  -webkit-line-clamp: 5;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
.heart-button {
  border: none;
  background-color: white;
  color: silver;
  font-size: 1.4rem;
}
.reply-box{
  display: flex;
  justify-content: center;
  margin-bottom: 0.8rem;
  margin-top: 0.5rem;
  width: 100%;
}
.reply-input{
  margin-right: 0.8rem;
  width: 15rem;
  height: 2.5rem;
}
.profile-box{
  display: flex;
  align-items: center;
}
.more-box{
  display: -webkit-box;
  min-width: 300px;
  width: inherit;
  white-space: normal;
  -webkit-box-orient: vertical;

}
.details-box{
  display: flex;
  justify-content: flex-end;
  text-decoration: underline;
}
.white-spce{
  height: 200px;
}
</style>
