<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <div id="sectionContainerCenter">
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9dc2cc40742c34cb8ae47195d3f14bbe"></script>
	<div id="mapContainer" style=" width:100%; height:100%;"></div>
	<script>
		var options = {
			center: new kakao.maps.LatLng(35.865572, 128.593432),
			level: 5
		};

		var map = new kakao.maps.Map(mapContainer, options);
	</script>
</div>