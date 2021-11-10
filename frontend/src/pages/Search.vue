<template>
  <div style="padding-bottom:100px;">
    <div style="display:flex; align-items:center;">
      <q-btn @click="goHome" round dense style="background:rgb(235,137,181); color:white; margin:0px 4.7rem 0px 20px;" icon="arrow_back" />
      <img src="../assets/horizon_logo.png" style="height:70px;">
    </div>
    <div style="display:flex; flex-direction:column; align-items:center; height:80px;">
      <div style="font-size:18px; font-weight:bold; margin-bottom:5px; margin-top:10px;">해시태그를 검색해주세요.</div>
      <div>
        <q-input @keyup.enter="searchSubmit" v-model="keyword" id="keyword" outlined dense label="ex)미역국" style="height:30px; width:200px;" />
        <span class="main_search_btn_span"><q-btn @click="searchSubmit" round
          style="background:rgb(235,137,181); color:white; float:right; left:50px; bottom:30px;" icon="search" /></span>
      </div>
    </div>
    <q-card class="my-card" flat bordered v-if="false">
      <q-card-section>
        <div style="display:flex;">
          <div class="text-overline text-orange-9">날짜</div>
        </div>
        <!-- 프로필 박스 -->
        <div class="profile-box">
          프로필이미지
          <!-- <img :src="para.user_img" alt="" class="profile-img" > -->
          <div class="text-h6 q-mt-sm q-mb-sm q-ml-sm">닉네임</div>
        </div>
        <div :class="extended ? 'more-box' : 'text-box'" transition: fade>
          <div @click="Detail(para)">
            내용
          </div>
        </div>
      </q-card-section>
      <q-separator />
      <!-- <q-img
        @click="Detail(para)"
        :src="para.img"
      /> -->
      <img src="../assets/baby.png" alt="">
      <q-separator />
      <q-card-actions style="display:flex; justify-content:space-between;">
        <div style="display:flex; align-items:center;">
          <button
          class="fas fa-heart heart-button"
          @click="getHeart"
          :style="heart ? 'color: red': 'color: silver'"/>
          <span>ㅁ명이 좋아요를 눌렀습니다.</span>
        </div>
        <div>댓글 개수 ㅁ개</div>
      </q-card-actions>
    </q-card>
  </div>
</template>

<script>
import { ref, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  setup(){
    const store = useStore()
    const router = useRouter()
    function searchSubmit() {
      // 검색api연결
    }
    function goHome(){
      router.push('home')
    }
    // 디테일로 이동
    const Detail = (para) => {
      store.commit('module/selectArticle', para)
      router.push({ name: "feed", params: { article_id: para.id } });
    };
    return {
      keyword : ref(''),
      goHome,
      searchSubmit,
      Detail
    }
  }
}
</script>

<style scoped>

</style>