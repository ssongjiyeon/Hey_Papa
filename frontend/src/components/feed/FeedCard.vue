<template>
<div style="width:100%; margin:0.5rem;">
  <head>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
  </head>
    <div>
      <q-card class="my-card" flat bordered>
        <div style="display:flex; flex-direction: row;">
          <div style="background-color:#C2D2F2; height: 10px; width:4rem; margin-left:1rem;">
          </div>
          <div>
          </div>
        </div>
        <q-card-section>
          <div style="display:flex; justify-content:space-between;">
            <div class="text-overline created-at">{{para.created_at.slice(0,10)}}</div>
            <div v-if="flag"><q-icon name="more_horiz" size="sm" @click="Show(para)"></q-icon></div>
          </div>
          <!-- 프로필 박스 -->
          <div class="profile-box">
            <img v-if="para.user_img=='NULL'" src="../../assets/default_user.png" class="profile-img">
            <img v-else :src="'https://k5b206.p.ssafy.io/api/static/img/'+para.user_img" class="profile-img">
            <div class="text-h6 q-mt-sm q-mb-sm q-ml-sm nickname">{{para.nickname}} 파파</div>
          </div>
          <div :class="extended ? 'more-box' : 'text-box'" transition: fade style="margin-top:0.5rem;">
            <div @click="Detail(para)" class="main_content">
              {{ para.content }}
            </div>
            <!-- 해쉬태그 -->
            <div style="padding-top:0.5rem; display:flex; font-family: 'Jua', sans-serif;">
              <div v-for="hashtag in para.hashtag" :key="hashtag">#{{ hashtag }}</div>
            </div>
          </div>
        </q-card-section>
        <q-separator />
        <img
          style="width:100%; height:370px;"
          @click="Detail(para)"
          :src="imgUrl"
        />
        <q-separator />
        <q-card-actions style="display:flex; justify-content:space-between;">
          <div style="display:flex; align-items:center;">
            <button
            class="fas fa-heart heart-button"
            @click="getHeart"
            :style="heart ? 'color: crimson': 'color: silver'"
            style="margin-left:-5px;"
            />
            <span style="font-family: 'GowunDodum-Regular';">{{para.like_cnt}}명이 이 글을 좋아합니다.</span>
          </div>
          <div class="speech-bubble" @click="Detail(para)">
            <i class="far fa-comment" style="font-size:21px;"></i>
            {{para.comment_cnt}}
          </div>
        </q-card-actions>
      </q-card>
    </div>
</div>
</template>

<script>
import { useQuasar } from 'quasar'
import { onMounted, ref,computed } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
export default {
  props: ["para"],
  setup(props) {
    var imgUrl = 'https://k5b206.p.ssafy.io/api/static/img/'
    const $q = useQuasar()
    const router = useRouter()
    const store = useStore()
    const heart = ref(false)
    var flag = ref(false)
    imgUrl = imgUrl + props.para.img
    if (localStorage.getItem('userId')==props.para.user_id){
      flag = true
    }
    if(props.para.like == true){
      heart.value = true
    }

    let article = computed(()=>props.para.like_cnt)
    function getHeart() {
      heart.value = !heart.value
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
    let tags = ""
    const hashtagList = props.para.hashtag
    const SplitHashtag = () => {
      if(hashtagList) {
        for (let i=0; hashtagList.length > i ; i++) {
          tags += "#" + hashtagList[i] + " "
        }
      }
    }
    onMounted(() => {
      SplitHashtag()
    })
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
        heart,
        tags
      }
    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Jua&family=Noto+Sans+KR&family=Gamja+Flower&display=swap');

.created-at{
  color: #5684BF;
  font-size: 1rem;
  font-family : 'Jua', sans-serif;
}
.nickname{
  /* font-family : 'Jua', sans-serif; */
  font-family : 'GowunDodum-Regular';
  font-size:22px;
  font-weight: bold;
  color: #5684BF;
  border-bottom: 5px solid pink;
}
.main_content{
  font-family: 'GowunDodum-Regular';
  font-size:0.9rem;
  font-weight: lighter;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
}
.my-card {
  width: 100%;
  background-color:#F2F2F2;
  border: 1px solid #C2D2F2;
  border-bottom: 11px solid #C2D2F2;
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
.hash-tag{
  font-size: 0.7rem;
  display: flex;

}
.heart-button {
  border: none;
  background-color: #F2F2F2;
  color: silver;
  font-size: 1.4rem;
}
.speech-bubble{
  font-weight:normal;
  font-size: 1.2rem;
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
  border-radius: 50%;
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
  border-radius: 1rem;
}
</style>
