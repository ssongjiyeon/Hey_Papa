<template>
  <div>
  <head>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
  </head>
      <!-- <q-btn-toggle
              glossy
              v-model="slide"
              :options="[
                { label: 1, value: 1 },
                { label: 2, value: 2 },
                { label: 3, value: 3 },
                { label: 4, value: 4 },
                { label: 5, value: 5 },
                { label: 6, value: 6 },

                ]"
            /> -->
      <div class="pagination">
        <q-pagination
          v-model="current"
          color="pink-3"
          :max="10"
          :max-pages="6"
          size="15px"
          unelevated
          padding="0px 15px 0px 15px"
          @click="page(current.value)"
        />
      </div>
    <div class="">
          <q-carousel
          v-model="current"
          height="600px"
          max-width="420px"
          >
          <q-carousel-slide :name="info.id"  v-for="info in infoList" :key="info" >
            <div class="column no-wrap ">
              <div class="slided" >
                <p style="font-family: 'Jua', sans-serif;">아이와 함께한 {{info.id}}개월차</p>
              </div>
              <div class="slided">
                <img style="width: 15rem;" :src="require(`../../assets/mom/${info.id}.png`)"/>
                <div class="title">지금 아이는</div>
                <div class="content">{{info.baby}}</div>
                <div class="title">지금 엄마는</div>
                <div class="content">{{info.mom}}</div>
              </div>
            </div>
          </q-carousel-slide>
        </q-carousel>
    </div>
  </div>

</template>

<script>
import { computed, ref } from 'vue'
import { ClosePopup } from 'quasar'
import { useStore } from 'vuex'

export default {
  setup () {
    const store = useStore()
    const slide = ref(1)
    const current = ref(1)
    const page = () => {
      slide.value = current.value
    }
    let infoList = computed(()=>
      store.getters['module/infoList']
    )
    const page2 = () => {
      current.value = slide.value
    }
    return {
      slide,
      fullscreen: ref(false),
      contentStyle: {
        backgroundColor: 'rgba(0,0,0,0.02)',
        color: '#555'
      },
      contentActiveStyle: {
        backgroundColor: '#eee',
        color: 'black'
      },
      thumbStyle: {
        right: '2px',
        borderRadius: '5px',
        backgroundColor: '#027be3',
        width: '5px',
        opacity: 0.75
      },
      current,
      page,
      infoList,
      page2
    }
  }
}
</script>


<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Jua&family=Noto+Sans+KR&family=Gamja+Flower&display=swap');
.title{
  /* font-family: 'Noto Sans KR', sans-serif; */
  font-family: 'Jua', sans-serif;
  display:flex;
  width:100%;
  font-size:25px;
  margin:10px 0px 10px 0px;
  /* font-weight:bold; */
}
.q-panel{ -ms-overflow-style: none; } .q-panel::-webkit-scrollbar{ display:none; }

.content{
  /* font-family: 'Jua', sans-serif; */
  font-family: 'Gamja Flower';
  font-size:20px;
}
.pagination{
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  margin-bottom:1rem;
}
.slided{
  display: flex;
  align-items: center;
  flex-direction: column;

}
.slided p {
  font-size: 1.5rem;
  margin-top: 1rem;
  font-weight: bold;
  color: rgb(97, 86, 86);
}
.slided img {
  margin-top: 0.5rem;
  height: 10rem;
}
.slided span {
  font-size: 1rem;
  margin-top: 1rem;
  font-weight: lighter;
  width: 13rem;
}

</style>
