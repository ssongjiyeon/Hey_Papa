<template>
  <div class="my_container">
    <h4>Hey PaPa!</h4>
    <div class="user_info">
      <img class="profile_img" style="margin-left: margin-right:20px;" src="http://placehold.it/100x100">
      <div class="nick_name">17주차 튼튼이 아빠</div>
    </div>
    <q-tabs
      v-model="tab"
      class="text-black"
      style="width:100%; margin-top:50px;"
    >
      <q-tab @click="goArticle" name="article" label="내 게시글" />
      <q-tab name="like" label="좋아요한 글" />
      <q-tab name="zzim" label="찜 문제" />
    </q-tabs>
    <q-tab-panels v-model="tab" animated
      style="background-color: whitesmoke; width:100%;">
      <span name="article" style="display:flex; flex-wrap:wrap;">
        <img v-for="myArticle in myArticles" :key="myArticle" :src="myArticle.img" style="width:33.3%">
      </span>
    </q-tab-panels>
    <q-tab-panels v-model="tab" animated style="background-color: whitesmoke; width:100%;">
      <span name="like" style="display:flex; flex-wrap:wrap;">
        <img v-for="mylike in myLikes" :key="mylike" :src="mylike.img" style="width:33.3%">
      </span>
    </q-tab-panels>
    <q-tab-panels v-model="tab" animated style="background-color: whitesmoke; width:100%;">
      <q-tab-panel name="zzim">
        찜 문제
      </q-tab-panel>
    </q-tab-panels>
  </div>
</template>

<script>
import { ref, computed } from 'vue'
import { useStore } from 'vuex'
export default {
  setup () {
    const store = useStore()
    const myArticles = computed(()=> store.getters['module/getMyarticle'])
    const myLikes = computed(()=> store.getters['module/getMylike'])
    // onMounted(()=>{
    //   store.dispatch('유저가쓴글받아오기')
    // })
    function goArticle(){
      console.log('아티클')
    }
    return {
      tab: ref('article'),
      myArticles,
      myLikes,
      goArticle
    }
  }
}
</script>

<style>
.my_container{
  display: flex;
  flex-direction: column;
  align-items: center;
}
.user_info{
  display: flex;
}
.profile_img{
  border-radius: 50%;
  margin-left: -30px;
  margin-right: 40px;
}
.nick_name{
  display: flex;
  align-items: center;
  font-weight: bold;
  font-size:20px;
}
</style>
