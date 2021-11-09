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
    <div class="">
      <q-pagination
        v-model="current"
        color="purple"
        :max="10"
        :max-pages="6"
        boundary-numbers
        @click="page(current.value)"
      />
    </div>
  <div class="">
        <q-carousel
        v-model="current"
        transition-prev="slide-right && page2"
        transition-next="slide-left && page2"

        animated
        arrows
        control-text-color="grey-8"
        height="600px"
        max-width="420px"
        class="bg-white text-black rounded-borders"

        >


        <q-carousel-slide :name="info.id"  v-for="info in infoList">
          <q-scroll-area class="fit">
            <div class="column no-wrap">
              <p>{{info.id}}개월차</p>
              <div>
                <img style="width: 15rem; " :src="require(`../../assets/mom/${info.id}.png`)"/>
              </div>
              <p>{{info.baby}}</p>



            </div>
          </q-scroll-area>
        </q-carousel-slide>
      </q-carousel>
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

i {
  font-size: 50px;
}
mom-img{
  width: 100px !important;


}

</style>
