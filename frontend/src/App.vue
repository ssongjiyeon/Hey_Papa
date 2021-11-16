<template>
  <div style="display:flex; justify-content: center;">
    <q-responsive :ratio="6/13" class="col" style="max-width: 420px;">
      <div class="app_container">
        <router-view />
      </div>
    </q-responsive>
  </div>
</template>
<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'App'
})
</script>
<style>
.app_container{
  /* border:1px solid black; */
  /* background-color: #F2F2F2; */
  color: #3B5E8C;

}
@font-face {
    font-family: 'Eulyoo1945-SemiBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2102-01@1.0/Eulyoo1945-SemiBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'Cafe24SsurroundAir';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/Cafe24SsurroundAir.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'GowunDodum-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/GowunDodum-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>
