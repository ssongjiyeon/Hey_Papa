<template>
<div>
<p>detail</p>
<p>{{feed.content}}</p>
<img :src="feed.imgurl" alt="x">
<div v-for="(reply,i) in replylist" key="i">
  <p>1{{reply}}</p>
</div>
</div>
</template>

<script>
import { ref, onMounted, computed } from 'vue'
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'
export default {
  setup(){
    const store = useStore()
    const route = useRoute()
    let replylist = []
    const feed = computed(()=>
      store.getters['module/feed']
    )




    // 답글불러오기
    onMounted(() => {
      const articleId = route.params.article_id
      console.log('params again', articleId)
      store.dispatch('module/getReply', articleId)
      .then((res) => {
        replylist = res.data
        console.log(replylist, 'fl')
      })
      .catch((err) => {
        console.log(err, 'err')
      })
      console.log('마운트')
    })

  return {
    feed,
    replylist
    // articleId
  }


  }
}
</script>

<style>

</style>
