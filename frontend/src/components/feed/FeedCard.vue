<template>
  <div style="width:100%; margin-bottom:20px;">
    <q-card class="my-card" flat bordered>
      <q-card-section>
        <div class="text-overline text-orange-9">{{para.created_at.slice(0,10)}}</div>
        <!-- 프로필 박스 -->
        <div class="profile-box">
            <img :src="para.user_img" alt="" class="profile-img" >
          <div class="text-h6 q-mt-sm q-mb-sm q-ml-sm">{{para.nickname}}</div>
        </div>
        <div :class="extended ? 'more-box' : 'text-box'" transition: fade>
          <div>
            {{ para.content }}
          </div>
        </div>
      </q-card-section>
      <q-separator />
      <q-img
        :src="para.img"
        v-if="para.img.length > 0"
      />
      <q-separator />
      <q-card-actions>
        <button
        class="fas fa-heart heart-button"
        @click="getHeart"
        :style="heart ? 'color: red': 'color: silver'"/>{{para.like_cnt}}명이 좋아요를 눌렀습니다.
        <q-space />
        댓글 개수 {{para.comment_cnt}}개
      </q-card-actions>
    </q-card>
  </div>
</template>

<script>
import { ref } from 'vue'
export default {
  props: ["para"],
  setup(props) {
  const heart = ref(false)
  const getHeart = () => {
    heart.value = !heart.value
    console.log(heart.value)
    }
    return {
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
.white-space{
  height: 50px !important;
}
.profile-img{
  height:2rem;
  width:2rem;
  box-shadow: 1px 2px 3px grey;
  border-radius: 1rem;
}
</style>
