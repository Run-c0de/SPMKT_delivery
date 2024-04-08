package com.example.supermercado_el_economico.Shop;

public class LocationData {

    private double newLatitude = 0.0;
    public double getLat(){
        return newLatitude;
    }
    private double newLongitude = 0.0;
    public double getLon(){
        return newLongitude;
    }
    private String _referente ;
    public String getReference(){
        return _referente;
    }

    public LocationData(double lat, double lon, String referente){
        newLatitude = lat;
        newLongitude = lon;
        _referente = referente;
    }



    private String txtLatitud;
    private String txtLongitud;

}
