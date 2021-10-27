<template>
  <div>
    <div style="height:50px; display:flex; justify-content:center; border:1px solid black;">
      Hey PaPa !
    </div>
    <div style="display:flex; flex-direction:column; align-items:center; height:80px;">
      <div style="font-size:18px; font-weight:bold; margin-bottom:5px;">어느 동네가 궁금하신가요?</div>
      <div>
        <q-input @keyup.enter="searchSubmit" v-model="keyword" id="keyword" outlined dense label="ex)유성구" style="height:30px; width:200px;" />
        <span class="main_search_btn_span"><q-btn @click="searchSubmit" round
          style="background:rgb(86,86,239); color:white; float:right; left:50px; bottom:30px;" icon="search" /></span>
      </div>
    </div>
    <div id="map" style="height:700px;"></div>
    <div class="custom_zoomcontrol radius_border" style="margin-top:100px;"> 
      <span @click="zoomIn"><span style="font-size:25px;">+</span></span>  
      <span @click="zoomOut"><span style="font-size:25px; font-weight:bold;">-</span></span>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      map: null,
      keyword : '',
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=37bb85b9260146017a9a127713c7187a&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(36.3549777,127.2983403),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      this.geocoder = new kakao.maps.services.Geocoder()
    },
    // 키워드 검색을 요청하는 함수입니다
    searchSubmit() {
      this.geocoder.addressSearch(this.keyword, (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
          let bounds = new kakao.maps.LatLngBounds();
          for (let i = 0; i < result.length; i++) {
            let data = result[i];
            bounds.extend(new kakao.maps.LatLng(data.y, data.x));
          }
          this.map.setBounds(bounds);
        }
      });
    },
    zoomIn() {
      this.map.setLevel(this.map.getLevel() - 1);
    },
    // 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
    zoomOut() {
      this.map.setLevel(this.map.getLevel() + 1);
    }
  }
}
</script>

<style>
#map{
  border:1px solid black;
}
.custom_zoomcontrol {position:absolute;top:50px;right:10px;width:36px;height:80px;overflow:hidden;z-index:1;background-color:#f5f5f5;} 
.custom_zoomcontrol span {display:block;width:36px;height:40px;text-align:center;cursor:pointer;}     
.custom_zoomcontrol span img {width:15px;height:15px;padding:12px 0;border:none;}             
.custom_zoomcontrol span:first-child{border-bottom:1px solid #bfbfbf;}
.radius_border{border:1px solid #919191;border-radius:5px;}  
</style>