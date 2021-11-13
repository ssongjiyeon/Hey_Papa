<template>
<head>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
</head>
  <div style="width:100%; margin:0.5rem;">
    <q-card class="my-card" flat bordered>
      <q-card-section>
        <div style="display:flex; justify-content:space-between;">
          <div class="text-overline text-orange-9">{{para.created_at.slice(0,10)}}</div>
          <div v-if="flag"><q-icon name="more_horiz" size="sm" @click="Show"></q-icon></div>
        </div>
        <!-- 프로필 박스 -->
        <div class="profile-box">
          <img v-if="para.user_img=='NULL'" src="../../assets/default_user.png" class="profile-img">
          <img v-else :src="'https://k5b206.p.ssafy.io/api/static/img/'+para.user_img" class="profile-img">
          <div class="text-h6 q-mt-sm q-mb-sm q-ml-sm">{{para.nickname}} 파파</div>
        </div>
        <div :class="extended ? 'more-box' : 'text-box'" transition: fade>
          <div @click="Detail(para)">
            {{ para.content }}
          </div>
          <!-- 해쉬태그 -->
          <div style="padding-top:0.5rem;">
            <p>태그{{ tags }}</p>
          </div>
        </div>
      </q-card-section>
      <q-separator />
      <img
        style="width:100%; height:350px;"
        @click="Detail(para)"
        :src="imgUrl"
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
        <div class="speech-bubble" @click="Detail(para)">
          <i class="far fa-comment"></i>
          {{para.comment_cnt}}
        </div>
      </q-card-actions>
    </q-card>
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
    // console.log(imgUrl)
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
        console.log(para,'야야!')
        store.commit('module/selectArticle', para)
        router.push({ name: "feed", params: { article_id: para.id } });
      };
    let tags = ""
    const hashtagList = props.para.hashtag
    const SplitHashtag = () => {
      if(hashtagList) {
        for (let i=0; hashtagList.length > i ; i++) {
          let hashtag = ""
          hashtag = hashtagList[i]
          tags += ("#" + hashtag + " ")
        }
      }
    }
    onMounted(() => {
      SplitHashtag()
      console.log(tags)
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
.hash-tag{
  font-size: 0.7rem;
  display: flex;

}
.heart-button {
  border: none;
  background-color: white;
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
