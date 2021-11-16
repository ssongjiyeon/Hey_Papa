<template>
  <div>
    <!-- <div style="display:flex; justify-content:center;">
      <img src="../assets/horizon_logo.png" class="logo">
    </div> -->
    <div class="my_top">
      <img src="../assets/horizon_logo.png" class="logo" style="height:70px;">
      <q-btn flat style="color:rgb(235,137,181);margin-left:50px; margin-right:10px;" icon="menu" @click="show()" />
    </div>
    <div style="display:flex; flex-direction:column; align-items:center; height:80px;">
      <div style="font-size:1.5rem; font-weight:bold; margin-bottom:5px; margin-top:10px;font-family: 'GowunDodum-Regular';">
        어느 동네가 궁금하신가요?
      </div>
      <div>
        <q-input @keyup.enter="searchSubmit" v-model="keyword" id="keyword" outlined dense label="ex)유성구" style="height:30px; width:200px;font-family: 'GowunDodum-Regular';" />
        <span class="main_search_btn_span"><q-btn @click="searchSubmit" round
          style="background:rgb(235,137,181); color:white; float:right; left:50px; bottom:30px;" icon="search" /></span>
      </div>
    </div>
    <div style="padding-bottom:110px;">
      <div id="map" style="height:600px;"></div>
      <div class="custom_zoomcontrol radius_border" style="margin-top:130px; margin-right:20px;">
        <span @click="zoomIn"><span style="font-size:25px;">+</span></span>
        <span @click="zoomOut"><span style="font-size:25px; font-weight:bold;">-</span></span>
      </div>
    </div>
  </div>
</template>
<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import { useQuasar } from 'quasar'
export default {
  data() {
    return {
      map: null,
      keyword : '',
      centers:[],
      window:[],
    };
  },
  setup() {
    const $q = useQuasar()
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
    return {
      show
    }
  },
  created(){
    const store = useStore()
    const user = computed(()=> store.getters['module/getUser'])
    this.carecenter(this.centers)
    this.keyword = user.value.region
    setTimeout(()=>{
      this.searchSubmit()
    },300)
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=b2fe1ce9adbf31d017ff5d0b7f10f639&libraries=services,clusterer";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(36.350461,127.3826247),
        level: 7,
      };
      this.map = new kakao.maps.Map(container, options);
      this.geocoder = new kakao.maps.services.Geocoder()
      // -----------------------지도 기본 세팅-------------------

     // 마커 표시하기
     setTimeout(() => {
       var markers = this.centers.map((i) => {
         // 마커 정보 생성
         var marker = new kakao.maps.Marker({
           map:this.map,
           position : new kakao.maps.LatLng(i.lat, i.lng),
         });
         marker.setMap(this.map)
         var infowindow = new kakao.maps.InfoWindow({
           content:i.content,
           position: new kakao.maps.LatLng(i.lat, i.lng),
           removable: true
         })
         this.window.push(infowindow)
         return marker
       });
       for (var i=0; i<markers.length;i++){
         kakao.maps.event.addListener(markers[i],'click',this.onclickfunc(this.map,markers[i],this.window[i]))
       }
     }, 300);
    },
    onclickfunc(map,marker,infowindow){
      return function(){
        infowindow.open(map,marker)
      }
    },
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
    carecenter(centers){
      const store = useStore()
      store.dispatch('module/getCarecenter')
        .then(function(res){
          res.data.forEach(e => {
            const center = {
              lat:e.latitude,
              lng:e.longitude,
              content :'<div class="wrap">' +
                        '    <div class="info">' +
                        '        <div class="title">' +
                                    e.name +
                        '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' +
                        '        </div>' +
                        '        <div class="body">' +
                        '            <div class="desc">' +
                        '                <div class="ellipsis"> 주소 : '+e.address+'</div>' +
                        '                <div class="jibun ellipsis">일반실 : ' + e.generalRoom + ' 특실 : '+e.specialRoom+'</div>' +
                        '                <div> 전화번호 : '+e.phoneNumber+'</div>' +
                        '            </div>' +
                        '        </div>' +
                        '    </div>' +
                        '</div>'
              }
            centers.push(center)
          });
        })
    },
    zoomIn() {
      this.map.setLevel(this.map.getLevel() - 1);
    },
    zoomOut() {
      this.map.setLevel(this.map.getLevel() + 1);
    },
  },
}
</script>

<style>
#map{
  margin:20px;
}
.custom_zoomcontrol {position:absolute;top:50px;right:10px;width:36px;height:80px;overflow:hidden;z-index:1;background-color:#f5f5f5;}
.custom_zoomcontrol span {display:block;width:36px;height:40px;text-align:center;cursor:pointer;}
.custom_zoomcontrol span img {width:15px;height:15px;padding:12px 0;border:none;}
.custom_zoomcontrol span:first-child{border-bottom:1px solid #bfbfbf;}
.radius_border{border:1px solid #919191;border-radius:5px;}
.logo{
  /* width:180px; */
  height:70px;
}
/* 커스텀오버레이 */
.wrap {font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
.wrap * {padding: 0;margin: 0;}
.wrap .info {width: 380px;height: 100px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
.wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
.body {margin-top:5px; display: flex; justify-content: center;}
.info .title {display: flex; justify-content: center; height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
.info .close:hover {cursor: pointer;}
.info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}

.my_top{
  display:flex;
  justify-content: center;
  margin-left:130px;
  margin-right:10px;
  margin-top: 5px;
}
</style>
