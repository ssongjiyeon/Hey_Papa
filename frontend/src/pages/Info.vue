<template>
  <div>
    <div style="display:flex; justify-content:center;">
      <img src="../assets/horizon_logo.png" style="height:70px;">
    </div>
    <InfoDetail />
  </div>
</template>

<script>
import InfoDetail from '../components/info/InfoDetail.vue'
import axios from 'axios'
import { onMounted, ref } from 'vue'
import { useStore } from 'vuex'
export default {
  components: { InfoDetail },
  setup(){
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
    onMounted(()=> {
      getInfo()
    })

    return {
      getInfo,
      InfoList
    }
  }

}
</script>

<style scoped>
</style>
