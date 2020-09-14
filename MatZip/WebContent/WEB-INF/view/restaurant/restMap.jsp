<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style>
.label {margin-bottom: 96px;}
.label * {display: inline-block;vertical-align: top;}
.label .left {background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_l.png") no-repeat;display: inline-block;height: 24px;overflow: hidden;vertical-align: top;width: 7px;}
.label .center {background: url(https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_bg.png) repeat-x;display: inline-block;height: 24px;font-size: 12px;line-height: 24px;}
.label .right {background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_r.png") -1px 0  no-repeat;display: inline-block;height: 24px;overflow: hidden;width: 6px;}
</style>
 
 <div id="sectionContainerCenter">
	<div id="mapContainer" style=" width:100%; height:100%;"></div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9dc2cc40742c34cb8ae47195d3f14bbe"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script>
	
	
		var options = {
			center: new kakao.maps.LatLng(35.865572, 128.593432),
			level: 5
		};

		var map = new kakao.maps.Map(mapContainer, options);
		
		console.log(map.getCenter())
		
		function getRestaurantList(){
			axios.get('/restaurant/ajaxGetList').then(function(res) {
				console.log(res.data)
			
				res.data.forEach(function(item) {
					createMarker(item)
					
					
				})
			})
		}
		
		getRestaurantList()
		
		
		if(navigator.geolocation) {									//OS와 브라우저가 현재 위치값을 서포티드 해줄 수 있냐. (null값이 리턴되면 실행 안됨. navigator.geolocation  != null과 같음)
		  console.log('Geolocation is supported!');
		  
		  var startPos;		  
		  navigator.geolocation.getCurrentPosition(function(pos) {		
			  	startPos = pos			  
			    console.log('lat : ' + startPos.coords.latitude)
			    console.log('lng : ' + startPos.coords.longitude)
			    
			    if(map) {
				    var moveLatLon = new kakao.maps.LatLng(startPos.coords.latitude, startPos.coords.longitude)
				    map.panTo(moveLatLon)			    	
			    }
		  });
		  
		} else {
		  console.log('Geolocation is not supported for this Browser/OS.');
		}
		
		
		
		function createMarker(item){
			
			var content = document.createElement('div')
			content.className = 'label'
			
			var leftSpan = document.createElement('span')
			leftSpan.className = 'left'
			
			var rightSpan = document.createElement('span')
			rightSpan.className = 'right'
			
			var centerSpan = document.createElement('span')
			centerSpan.className = 'center'
			centerSpan.innerText = item.nm

			content.appendChild(leftSpan)
			content.appendChild(centerSpan)
			content.appendChild(rightSpan)
			
			
			//var ctnt = `<div class ="label"><span class="left"></span><span class="center">\${item.nm}</span><span class="right"></span></div>`;
			var mPos = new kakao.maps.LatLng(item.lat, item.lng)

			var marker = new kakao.maps.CustomOverlay({
				position: mPos,
				content: content
				
			});
			
			addEvent(content, 'click', function () {
				console.log('마커클릭: ' + item.i_rest)
				moveToDetail(item.i_rest)
			})
			
			marker.setMap(map)
			
		}
		
		
		function moveToDetail(i_rest){
			location.href='/restaurant/restDetail?i_rest=' + i_rest
		}
		
		function addEvent(target,type, callback){
			if(target.addEventListener){
				target.addEventListener(type, callback);
			} else {
				target.attachEvent('on' + type, callback);
			}
		}
		
	</script>
</div>