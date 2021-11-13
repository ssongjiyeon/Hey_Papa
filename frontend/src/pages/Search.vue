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
    <div id="articles" class="q-pa-xs row items-start card-box">
      <FeedCard v-for="para in articles" :key="para" :para="para"/>
      <!-- @click="Detail(para.id)&&Backup(para.content, para.imgUrl)"  -->
    </div>
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
    const keyword = ref('')
    var articles = computed(()=> store.getters['module/searchArticle'])
    function searchSubmit() {
      // 검색api연결
      const object = {
        hashtag: keyword.value,
        user_id: localStorage.getItem('userId')
      }
      store.dispatch('module/search',object).then((res)=>{
        console.log(res.data,'res')
        store.commit('module/allSearchArticle', res.data)
      })
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
      keyword,
      goHome,
      articles,
      searchSubmit,
      Detail
    }
  }
}
</script>

<style scoped>
.card-box {
  width: 100%;
  min-width: 30px;
  /* overflow: scroll; */
}
</style>