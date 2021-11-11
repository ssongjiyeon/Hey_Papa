<template>
  <div style="width:100%; margin-bottom:20px;">
    <q-card class="my-card" flat bordered>
      <q-card-section>
        <div style="display:flex; justify-content:space-between;">
          <div class="text-overline text-orange-9">{{para.created_at.slice(0,10)}}</div>
          <div v-if="flag"><q-icon name="more_horiz" size="sm" @click="Show"></q-icon></div>
        </div>
        <!-- 프로필 박스 -->
        <div class="profile-box">
          <img :src="para.user_img" alt="" class="profile-img" >
          <div class="text-h6 q-mt-sm q-mb-sm q-ml-sm">{{para.nickname}}</div>
        </div>
        <div :class="extended ? 'more-box' : 'text-box'" transition: fade>
          <div @click="Detail(para)">
            {{ para.content }}
          </div>
        </div>
      </q-card-section>
      <q-separator />
      <img
        style="width:100%; height:350px;"
        @click="Detail(para)"
        src="/home/ubuntu/img/article/10-2021-11-10-05-52-52-red.png"
      />
      <q-separator />
      <q-card-actions style="display:flex; justify-content:space-between;">
        <div style="display:flex; align-items:center;">
          <button
          class="fas fa-heart heart-button"
          @click="getHeart"
          :style="heart ? 'color: red': 'color: silver'"/>
          <span>{{para.like_cnt}}명이 좋아요를 눌렀습니다.</span>
        </div>
        <div>댓글 개수 {{para.comment_cnt}}개</div>
      </q-card-actions>
    </q-card>
  </div>
</template>

<script>
import { useQuasar } from 'quasar'
import { ref,computed } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
export default {
  props: ["para"],
  setup(props) {
    var imgUrl = 'https://k5b206.p.ssafy.io:8080/api/static/img/'
    const $q = useQuasar()
    const router = useRouter()
    const store = useStore()
    const heart = ref(false)
    var flag = ref(false)
    imgUrl = imgUrl + props.para.img
    console.log(imgUrl)
    if (localStorage.getItem('userId')==props.para.user_id){
      flag = true
    }
    if(props.para.like == true){
      heart.value = true
    }
    
    let article = computed(()=>props.para.like_cnt)
    function getHeart() {
      heart.value = !heart.value
      console.log(heart.value)
      store.dispatch('module/likeArticle',{
        id:props.para.id,check:heart.value,user_id:localStorage.getItem('userId')}).then(()=>{
          store.dispatch('module/allArticle',localStorage.getItem('userId')).then((res)=>{
            store.commit('module/setAllarticle', res.data)
          })
        })
      }
    const Detail = (para) => {
        store.commit('module/selectArticle', para)
        router.push({ name: "feed", params: { article_id: para.id } });
      };
    function Show () {
        $q.bottomSheet({
          message: '메뉴',
          actions: [
            // {},
            {
              label: '게시글 수정',
              icon: 'edit',
              id: 'edit'
            },
            {},
            {
              label: '게시글 삭제',
              icon: 'delete',
              id: 'delete'
            },
          ]
        }).onOk(action => {
          if (action.id == 'edit'){
            store.commit('module/selectArticle', props.para)
            router.push('modify')
          }
          else if (action.id == 'delete'){
            store.dispatch('module/deleteArticle',props.para.id).then(()=>{
              store.dispatch('module/allArticle',localStorage.getItem('userId')).then((res)=>{
                store.commit('module/setAllarticle', res.data)
                router.go()
              })
            })
        }
      }).onCancel(() => {
        // console.log('바텀시트 빠져나올때')
      }).onDismiss(() => {
        // console.log('I am triggered on both OK and Cancel')
      })
    }
    return {
        lorem: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
        Detail,
        expanded: ref(false),
        extended: ref(false),
        imgUrl,
        getHeart,
        Show,
        flag,
        article,
        heart
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
