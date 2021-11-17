<template>
  <div>
    <div class="my_top">
      <img src="../assets/horizon_logo.png" class="logo" style="height:70px;">
      <q-btn flat style="color:rgb(235,137,181);margin-left:50px; margin-right:10px;" icon="menu" @click="show()" />
    </div>
    <InfoDetail />
  </div>
</template>

<script>
import InfoDetail from '../components/info/InfoDetail.vue'
import axios from 'axios'
import { onMounted, ref } from 'vue'
import { useStore } from 'vuex'
import { useQuasar } from 'quasar'

export default {
  components: { InfoDetail },
  setup(){
    const $q = useQuasar()
    const store = useStore()
    let InfoList = ref([])
    const getInfo = () => {
      axios({
        method: 'GET',
        url: 'https://k5b206.p.ssafy.io/api/info'
      })
      .then((res) => {
        InfoList = res.data
        store.commit('module/infoList', InfoList)

      })
      .catch((err) => {
        console.log(err)
      })
    }
    function show () {
      $q.bottomSheet({
        message: '메뉴',
        actions: [
          {
            label: '회원정보수정',
            icon: 'account_circle',
            id: 'update'
          },
          {},
          {
            label: '로그아웃',
            icon: 'logout',
            id: 'logout'
          },
        ]
      }).onOk(action => {
        if (action.id == 'update'){
          router.push('set')
        }
        else if (action.id == 'logout'){
          store.dispatch('module/logout').then(()=>{
            router.push('/')
          })
        }
      }).onCancel(() => {
        // console.log('바텀시트 빠져나올때')
      }).onDismiss(() => {
        // console.log('I am triggered on both OK and Cancel')
      })
    }
    onMounted(()=> {
      getInfo()
    })

    return {
      getInfo,
      InfoList,
      show
    }
  }

}
</script>

<style scoped>
.my_top{
  display:flex;
  justify-content: center;
  margin-left:130px;
  margin-right:10px;
  margin-top: 5px;
}
</style>
