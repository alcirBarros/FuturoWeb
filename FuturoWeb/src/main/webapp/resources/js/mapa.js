
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
    if (markersArray[id] == null) {
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

        //  console.log(id, lant, long);
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


function historicoCoordenadas() {

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

    var polylineOptions = {
        map: map,
        path: points,
        strokeColor: '#FF0000',
        strokeOpacity: 0.8,
        strokeWeight: 5
    }

    var polyline = new google.maps.Polyline(polylineOptions);

}