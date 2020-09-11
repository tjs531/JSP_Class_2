<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
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
					const na = {
							'Ga' : item.lng,
							'Ha' : item.lat
					}
					const marker = new kakao.maps.Marker({
						position: na
					})
					
					marker.setMap(map)
				})
			})
		}
		
		getRestaurantList()
		/*
		na : {
			Ga: 128.5934206205559
			Ha: 35.86555867253387
		}
		*/
	</script>
</div>