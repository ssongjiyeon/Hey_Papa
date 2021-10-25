<template>
<div class="quiz-text">
  <h5 style="margin: 0.5rem">hey! papa</h5>
  <h3 style="margin: 0.5rem">Quiz</h3>
</div>

<div class="card-box" q-pa-lg row item-start q-gutter-md >
  <q-card class="my-card" v-for="theme in themes" :key=theme.id>
    <img :src="theme.imgUrl">

    <q-card-section class="card-section">
      <div class="text-h6">{{theme.theme}}</div>

    </q-card-section>
  </q-card>


</div>
</template>

<script>
export default {
  setup() {
    let themes = [
      {
        id: 1,
        theme: '아기',
        imgUrl: "https://ifh.cc/g/0nA9O0.png"
      },
      {
        id: 2,
        theme: '음식',
        imgUrl: "https://ifh.cc/g/sOxIcm.png"
      },
      {
        id: 3,
        theme: '사회',
        imgUrl: "https://ifh.cc/g/gSUEJu.png"
      },
      {
        id: 4,
        theme: '아내',
        imgUrl: "https://ifh.cc/g/lajTXv.png"
      },
    ]


    return {
      themes
    }
  }
}
</script>

<style scoped>
.quiz-text {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin: 1rem;
}

.card-box {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-evenly;
  margin-top: 4rem;

}

.my-card {
  max-width: 9rem;
  margin-bottom: 1rem;

}

.my-card img {
  height: 9rem;
}

.card-section{
  height: 2rem;
  display: flex;
  align-items: center;
  justify-content: center;
}
.text-h6{
  font-size: 0.8rem;
  padding: 0;
  font-weight: bold;
}


</style>

