
var map;
var centerMap;
var myOptions;
var markersArray = new Object();

function initialize() {
    centerMap = new google.maps.LatLng(-22.7271525, -45.1199495);
    myOptions = {zoom: 3, center: centerMap, mapTypeId: google.maps.MapTypeId.ROADMAP };
    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
}


function addMarker(id, lant, long) {
    if(markersArray[id] == null){
        var latLng = new google.maps.LatLng(lant, long);
        var marker = new google.maps.Marker({
            position: latLng,
            map: map,
            title: 'Teste',
            html: 'TESTE HTML',
            icon: '../../resources/images/icons/hospital-map.png'
        });
        
        array  = new Object();
        array[1] = marker;
        markersArray[id] = array;
        
        console.log(id, lant, long);
    }
}

function coodenada(data) {
    for (var i in data) {
        this.addMarker(data[i].id, data[i].latitude, data[i].longitude);
    }
}