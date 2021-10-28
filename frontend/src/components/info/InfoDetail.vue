<template>
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
    <div class="q-pa-lg flex flex-center">
    <q-pagination
      v-model="current"
      color="purple"
      :max="12"
      :max-pages="6"
      boundary-numbers
      @click="page(current.value)"
    />
    </div>
  <div class="q-pa-md">
        <q-carousel
        v-model="slide"
        swipeable
        animated
        arrows
        control-text-color="grey-8"
        height="600px"
        max-width="420px"
        class="bg-white text-black rounded-borders"
        >


        <q-carousel-slide :name="info.id"  v-for="info in InfoList">
          <q-scroll-area class="fit">
            <div class="column no-wrap flex-center q-carousel--padding">
              <i class="fas fa-baby"></i>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>
              <p>{{info.id}}</p>


            </div>
          </q-scroll-area>
        </q-carousel-slide>
      </q-carousel>
  </div>

</template>

<script>
import { ref } from 'vue'

export default {
  setup () {

    const slide = ref(1)
    const current = ref(1)
    const page = () => {
      slide.value = current.value
    }
    const InfoList = [
      {
        id: 1,
        imgUrl: "https://blog.kakaocdn.net/dn/vcwsV/btqBaASZnrH/qlZ29dOuW6qkzh4Nftjz2K/img.jpg",
      },
      {
        id: 2,
        imgUrl: "https://imgnews.pstatic.net/image/421/2021/10/25/0005679808_001_20211025224001968.jpg?type=w647",
      },
      {
        id: 3,
        imgUrl: "https://ifh.cc/g/8gYLR9.jpg"
      },
    ]


    return {
      slide,
      fullscreen: ref(false),
      InfoList,
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
      page
    }
  }
}
</script>



<style scoped>

i {
  font-size: 50px;
}

</style>
