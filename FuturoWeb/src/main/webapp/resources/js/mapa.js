
var map;
var centerMap;
var myOptions;
var markersArray = new Object();

function initialize() {
    centerMap = new google.maps.LatLng(-22.7271525, -45.1199495);
    myOptions = {zoom: 3, center: centerMap, mapTypeId: google.maps.MapTypeId.ROADMAP};
    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
}


function addMarker(id, lant, long) {
    if (markersArray[id] != null ) {
        var latLng = new google.maps.LatLng(lant, long);
        var marker = new google.maps.Marker({
            position: latLng,
            map: map,
            title: 'Teste',
            html: 'TESTE HTML',
            icon: '../../resources/images/icons/hospital-map.png'
        });

        array = new Object();
        array[1] = marker;
        markersArray[id] = array;
    }else{
        for (i in markersArray) {
            markersArray[i][1].setPosition(new google.maps.LatLng(lant, long));
        }
    }
}

function coodenada(data) {
    var points = [];
    
    for (var i in data) {
        this.addMarker(data[i].id, data[i].latitude, data[i].longitude);
        
        for(var h in data[i].historicoCoodenadas){
            points.push(new google.maps.LatLng(data[i].historicoCoodenadas[h].latitude, data[i].historicoCoodenadas[h].longitude));
        }
    }

    var polylineOptions = {map: map, path: points, strokeColor: '#FF0000', strokeOpacity: 0.8, strokeWeight: 5 }

    var polyline = new google.maps.Polyline(polylineOptions);

}

function mover(data) {

   // console.log(data);
    for (var i in data) {
      //  console.log(data[i]);
        var dadosLatLng = new google.maps.LatLng(data[i].latitude, data[i].longitude);
        var marker = new google.maps.Marker({
            position: dadosLatLng,
            map: map
        });
    }
}


