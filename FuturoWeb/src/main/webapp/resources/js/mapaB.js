var infowindow = null;
var dadosLatLng;

var map;
//var markersArray = [];

var markersArray = new Object();

document.onmousemove = coordinates_mouse;



function mover(data) {

    for (var i in data) {
        console.log(data[i]);
        var dadosLatLng = new google.maps.LatLng(data[i].latitude, data[i].longitude);
        var marker = new google.maps.Marker({
            position: dadosLatLng,
            map: map
        });
    }
}

function coordinates_mouse(event){
    ev = event || window.event;
    document.getElementById("latlgn").style.top = ev.pageY + "px";
    document.getElementById("latlgn").style.left = ev.pageX + "px";
}

function initialize() {
    
    var bounds = new google.maps.LatLngBounds( new google.maps.LatLng(-24.444315284119522, -47.61790992421481),
                                               new google.maps.LatLng(-22.647684715880477, -45.65809007578519) );


    var points = [new google.maps.LatLng(-22.7271525, -45.1199495),
        new google.maps.LatLng(-30.030442381294925, -51.20699644088745),
        new google.maps.LatLng(-30.030349495320916, -51.2077260017395),
        new google.maps.LatLng(-30.030200877581475, -51.20845556259155),
        new google.maps.LatLng(-30.031129734797137, -51.20847702026367),
        new google.maps.LatLng(-30.031891391218515, -51.20845556259155),
        new google.maps.LatLng(-30.03293169297124, -51.20845556259155),
        new google.maps.LatLng(-30.03465931282814, -51.208412647247314),
        new google.maps.LatLng(-30.03465931282814 - 51.20905637741089),
        new google.maps.LatLng(-30.03467788922207, -51.20905637741089)];

    var centerMap = new google.maps.LatLng(-22.7271525, -45.1199495);
    

    var myOptions = {
        zoom: 004,
        center: centerMap,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }

    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
    
    
    rect = new google.maps.Rectangle({
           bounds: bounds,
           fillColor:"#FF0000",
           fillOpacity: 0.5,
           strokeColor:"#FFFF00",
           strokeOpacity: 0.5,
           strokeWeight: 2,
           zIndex: 5
        });
        rect.setMap(map);

    google.maps.event.addListener(map, "mousemove", function(pt) {
       document.getElementById("latlgn").innerHTML = pt.latLng;   
    });

    addMarker();
    // setMarkers(map, '', 'hospital-map.png');
    // setMarkers(map, enderecos, 'family-map.png');

    var polylineOptions = {
        map: map,
        path: points,
        strokeColor: '#FF0000',
        strokeOpacity: 0.8,
        strokeWeight: 5
    }

    var polyline = new google.maps.Polyline(polylineOptions);



    infowindow = new google.maps.InfoWindow({
        content: "loading...",
        width: 200
    });
}


function addMarker() {
    
    latLng = new google.maps.LatLng(-22.7271525, -45.1199495);

    marker = new google.maps.Marker({
        position: latLng,
        map: map,
        title: 'Teste',
        html: 'TESTE HTML',
        icon: '../../resources/images/icons/hospital-map.png'
    }); 
  
  //markersArray.push(marker);
  
  markersArray[0] = marker;
}

function clearOverlays() {
  if (markersArray) {
    for (i in markersArray) {
      markersArray[i].setMap(null);
    }
  }
}

function moverMarker() {
    setTimeout(function() {

    for (i in markersArray) {

        markersArray[i].setPosition( new google.maps.LatLng( 0, 0 ) );
      //  map.panTo(new google.maps.LatLng(0, 0));
     }

    }, 1500);
};


//function setMarkers(map, markers, icon) {
//    //  for (var i = 0; i < markers.length; i++) {
//    //  var dados = markers[i];
//    //  var dadosLatLng = new google.maps.LatLng(dados[1], dados[2]);
//    
//    latLng = new google.maps.LatLng(-22.7271525, -45.1199495);
//
//    var marker = new google.maps.Marker({
//        position: latLng,
//        map: map,
//        title: 'Teste',
//        html: 'TESTE HTML',
//        icon: '../../resources/images/icons/' + icon
//    });
//
////    google.maps.event.addListener(marker, "click", function() {
////        infowindow.setContent(this.html);
////        infowindow.open(map, this);
////    });
//
//}
